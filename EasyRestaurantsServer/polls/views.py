from django.contrib.auth import authenticate
from django.shortcuts import render

from django.http import HttpResponse
from django.http import HttpResponseRedirect
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser
from rest_framework.renderers import JSONRenderer
from polls.forms import *
from silk.profiling.profiler import silk_profile
from datetime import datetime

@csrf_exempt
@silk_profile(name='Registration')
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
@silk_profile(name='Login')
def login_api(request):
    if request.method == 'POST':
        data = JSONParser().parse(request)
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

@csrf_exempt
@silk_profile(name='Restaurants')
def restaurants_api(request):
    if request.method == 'GET':
        try:
            user = User.objects.all().get(username=request.GET['user'])
            filtered = Client.objects.all().get(user=user).favourites.all()
        except:
            qdict={}
            for key in request.GET.keys():
                qdict[key]= request.GET[key]
            filtered = Restaurant.objects.all().filter(**qdict)
        restaurants = []
        for restaurant in filtered:
            c = {}
            c['id'] = restaurant.id
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
@silk_profile(name='Reservations')
def reservations_api(request):
    if request.method == 'POST':
        data = JSONParser().parse(request)
        try:
            user = User.objects.get(username=data['user'])
            r = Reservation.objects.create(user=Client.objects.all().get(user=user), restaurant=Restaurant.objects.get(name=data['restaurant']),
                                       date=data['date'], number_clients=data['number_clients'], comments=data['comments'])
            r.restaurant.capacity -= r.number_clients
            return JsonResponse("Reservation created", safe=False, status=200)
        except:
            return JsonResponse("Wrong details", safe=False, status=403)
    elif request.method == 'GET':
        try:
            user = User.objects.get(username=request.GET['user'])
            client = Client.objects.get(user=user)
            reservations = []
            for reservation in Reservation.objects.filter(user=client):
                c = {}
                c['date'] = reservation.date
                c['number_clients'] = reservation.number_clients
                c['restaurant'] = reservation.restaurant.name
                c['id'] = reservation.id
                reservations.append(c)
            return JsonResponse(reservations, safe=False, status=200)
        except:
            return JsonResponse("User doesn't exist", safe=False, status=403)



@csrf_exempt
@silk_profile(name='Reviews')
def reviews_api(request):
    if request.method == 'GET':
        try:
            restaurant = Restaurant.objects.all().get(name=str(request.GET['restaurant']))
            reviews = Review.objects.all().filter(restaurant=restaurant)
        except:
            return JsonResponse("Restaurant doesn't exist", safe=False, status=403)
        r = []
        for review in reviews:
            c = {}
            c['id'] = review.id
            c['date'] = review.date
            c['comments'] = review.comments
            c['score'] = review.score
            r.append(c)
        return JsonResponse(r, safe=False, status=200)
    else:
        data = JSONParser().parse(request)
        try:
            restaurant = Restaurant.objects.get(name=data['restaurant'])
            new = Review.objects.create(date=datetime.now(), comments=data['comments'], score=data['score'],
                                        restaurant=restaurant)
            all_reviews = Review.objects.all().filter(restaurant=restaurant)
            score = 0
            for review in all_reviews:
                score += review.score
            restaurant.score = round(score/len(all_reviews), 3)
            restaurant.save()
            return JsonResponse("Created", safe=False, status=200)
        except:
            return JsonResponse("Incorrect data", safe=False, status=403)


@csrf_exempt
@silk_profile(name='Delete_reservation')
def delete_reservation(request):
    if request.method == 'GET':
        try:
            reservation = Reservation.objects.all().get(id=int(request.GET['id']))
            reservation.delete()
            return JsonResponse("Removed", safe=False, status=200)
        except:
            return JsonResponse("Reservation doesn't exist", safe=False, status=403)


@csrf_exempt
@silk_profile(name='Update_reservation')
def update_reservation(request):
    if request.method == 'POST':
        data = JSONParser().parse(request)
        try:
            reservation = Reservation.objects.all().get(id=int(data['id']))
            reservation.date=data['date']
            reservation.number_clients=int(data['number_clients'])
            reservation.save()
            return JsonResponse("Updated", safe=False, status=200)
        except:
            return JsonResponse("Reservation doesn't exist", safe=False, status=403)

@csrf_exempt
@silk_profile(name='Favourite')
def make_favourite(request):
    if request.method == 'POST':
        data = JSONParser().parse(request)
        try:
            restaurant = Restaurant.objects.all().get(id=int(data['id']))
            user = User.objects.all().get(username=data['user'])
            client = Client.objects.all().get(user=user)
            if restaurant in client.favourites.all():
                client.favourites.remove(restaurant)
            else:
                client.favourites.add(restaurant)
            client.save()
            return JsonResponse("Updated", safe=False, status=200)
        except:
            return JsonResponse("Reservation doesn't exist", safe=False, status=403)