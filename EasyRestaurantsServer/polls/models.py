from django.db import models
from django.contrib.auth.models import User


class Restaurant(models.Model):
    name = models.CharField(max_length=50)
    location = models.CharField(max_length=100)
    speciality = models.CharField(max_length=30)
    menu = models.CharField(max_length=500, blank=True)
    score = models.FloatField(blank=True)
    capacity = models.IntegerField()
    schedule = models.CharField(max_length=40)
    type = models.CharField(max_length=30)

    def __str__(self):
        return self.name


class Client(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    favourites = models.ManyToManyField(Restaurant, blank=True)

    def __str__(self):
        return self.user.username



class Reservation(models.Model):
    date = models.DateTimeField()
    number_clients = models.IntegerField()
    comments = models.CharField(max_length=200, blank=True)
    restaurant = models.ForeignKey(Restaurant, on_delete=models.CASCADE)
    user = models.ForeignKey(Client, on_delete=models.CASCADE)

    def __str__(self):
        return str(self.date)
