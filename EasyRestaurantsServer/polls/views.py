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


