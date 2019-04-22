from django.contrib.auth import authenticate
from django.shortcuts import render

from django.http import HttpResponse
from django.http import HttpResponseRedirect
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser
from rest_framework.renderers import JSONRenderer
from polls.forms import *

@csrf_exempt
def register_api(request):
    if request.method == 'POST':
        data = JSONParser().parse(request)
        if not User.objects.filter(username=data['username']).exists():
            new = User.objects.create()
            new.username = data['username']
            new.first_name = data['name']
            new.email = data['email']
            new.set_password(data['password'])
            new.save()

            Client.objects.create(user=new)
            return JsonResponse("User created", safe=False, status=200)
        else:
            return JsonResponse("Username already exists", safe=False, status=403)

@csrf_exempt
def login_api(request):
    if request.method == 'POST':
        data = JSONParser().parse(request)
        # print(User.objects.filter(username=data['username']).exists())
        if User.objects.filter(username=data['username']).exists():
            user = authenticate(username=data['username'], password=data['password'])
            try:
                favourites = Client.objects.get(user=user).favourites
                favs =[restaurant['name'] for restaurant in favourites.values()]
            except:
                favs = []
            if user is not None:
                return JsonResponse(favs, status=200, safe=False)
            else:
                return JsonResponse("Invalid credentials", status=403, safe=False)
        else:
            return JsonResponse("Invalid user", status=403, safe=False)




#Used for both getting favourites restaurants of a user or filtering all restaurants
@csrf_exempt
def restaurants_api(request):
    if request.method == 'GET':
        try:
            user = User.objects.all().get(username=request.GET['user'])
            filtered = Client.objects.all().get(user=user).favourites.all()
        except:
            qdict={}
            for key in request.GET.keys(): #Builds dicctionary for the query to filter restaurants
                qdict[key]= request.GET[key]
            filtered = Restaurant.objects.all().filter(**qdict)
        restaurants = []
        for restaurant in filtered:
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
        return JsonResponse(restaurants, safe=False, status=200)


@csrf_exempt
def reservations_api(request):
    if request.method == 'POST':
        data = JSONParser().parse(request)
        if User.objects.filter(username=data['user']).exists() and Restaurant.objects.filter(name=data['restaurant']).exists():
            user = User.objects.filter(username=data['user'])
            r = Reservation.objects.create(user=Client.objects.get(user=user), restaurant=Restaurant.objects.get(name=data['restaurant']),
                                       date=data['date'], number_clients=data['number_clients'], comments=data['comments'])
            r.restaurant.capacity -= r.number_clients
            return JsonResponse("Reservation created", safe=False, status=200)
        else:
            return JsonResponse("Wrong details", safe=False, status=403)
    elif request.method == 'GET':
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
            return JsonResponse(reservations, safe=False, status=200)
        else:
            return JsonResponse("User doesn't exist", safe=False, status=403)

