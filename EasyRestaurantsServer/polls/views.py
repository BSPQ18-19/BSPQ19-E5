from django.contrib.auth import authenticate
from django.shortcuts import render

# Create your views here.

from django.http import HttpResponse
from django.http import HttpResponseRedirect
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser
from rest_framework.renderers import JSONRenderer
from polls.forms import *

@csrf_exempt
def client_api(request):
    if request.method == 'POST':
        data = JSONParser().parse(request)
        if not User.objects.filter(username=data['username']).exists():
            new = User.objects.create()
            new.username = data['username']
            new.first_name = data['name']
            new.password = data['password']
            new.email = data['email']
            new.save()

            cl = Client.objects.create(user=new)
            return JsonResponse("User created", safe=False)
        else:
            return JsonResponse("Username already exists", safe=False)

@csrf_exempt
def login_api(request):
    if request.method == 'POST':
        data = JSONParser().parse(request)
        user = authenticate(username=data['username'], password=data['password'])
        favourites = Client.objects.get(user=user).favourites
        favs = [restaurant['name'] for restaurant in favourites.values()]
        if user is not None:
            return JsonResponse(favs, safe=False)
        else:
            return JsonResponse("Invalid credentials", safe=False)


@csrf_exempt
def restaurants_api(request):
    if request.method == 'GET':
        restaurants = []
        for restaurant in Restaurant.objects.all():
            c = {}
            c['name'] = restaurant.name
            c['location'] = restaurant.location
            c['speciality'] = restaurant.speciality
            c['menu'] = restaurant.menu
            c['score'] = restaurant.score
            c['capacity'] = restaurant.score
            c['schedule'] = restaurant.schedule
            c['type'] = restaurant.type
            restaurants.append(c)
        return JsonResponse(restaurants, safe=False)

@csrf_exempt
def reservations_api(request):
    if request.method == 'POST':
        data = JSONParser().parse(request)
        if User.objects.filter(username=data['user']).exists() and Restaurant.objects.filter(name=data['restaurant']).exists():
            user = User.objects.filter(username=data['user'])
            r = Reservation.objects.create(user=Client.objects.get(user=user), restaurant=Restaurant.objects.get(name=data['restaurant']),
                                       date=data['date'], number_clients=data['number_clients'], comments=data['comments'])
            r.restaurant.capacity -= r.number_clients
            return JsonResponse("Reservation created", safe=False)
        else:
            return JsonResponse("Wrong details", safe=False)
    elif request.method == 'GET':
        # data = JSONParser().parse(request)
        if User.objects.filter(username=request.GET['user']).exists():
            user = User.objects.get(username=request.GET['user'])
            client = Client.objects.get(user=user)
            reservations = []
            for reservation in Reservation.objects.filter(user=client):
                c = {}
                c['date'] = reservation.date
                c['number_clients'] = reservation.number_clients
                c['restaurant'] = reservation.restaurant.name
                reservations.append(c)
            return JsonResponse(reservations, safe=False)
        else:
            return JsonResponse("User doesn't exist", safe=False)

