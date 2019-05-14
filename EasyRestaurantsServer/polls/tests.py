import json

from django.test import TestCase
from rest_framework.parsers import JSONParser

# Create your tests here.
from django.urls import reverse
from django.contrib.auth.models import User
from .models import Client, Restaurant, Reservation, Review
class TestViews(TestCase):
    def test_restaurants(self):
        url = reverse("restaurant")
        resp = self.client.get(url)
        self.assertEqual(resp.status_code, 200)

    def test_register(self):
        url = reverse("registration")
        data = b'{ "username": "test", "password": "test1234", "email": "dsad@dsad.da", "name": "Test"}'
        resp = self.client.post(url, data=data, content_type="application/json")
        self.assertEqual(resp.status_code, 200)

    def test_login(self):
        url = reverse("login")
        user = User.objects.create(username='carlos')
        user.set_password("numero1234")
        user.save()
        data =b'{"username": "carlos", "password": "numero1234"}'
        resp = self.client.post(url,data=data, content_type="application/json")
        self.assertEqual(resp.status_code, 200)

    def test_reservations(self):
        url = reverse("reservation")
        user = User.objects.create(username='carlos')
        user.set_password("numero1234")
        user.save()
        r = Restaurant.objects.create(name="Txacoli",location="Artxanda", speciality="Grill"
                                  , menu="", score=3.0, capacity=40, schedule="12:00/15:00", type="asador")
        cl = Client.objects.create(user=user)
        cl.favourites.add(r)
        cl.save()
        data = b'{"date": "2019-04-12 23:00", "number_clients": 3, "user": "carlos", "restaurant": "Txacoli", "comments": ""}'
        resp = self.client.post(url, data=data, content_type="application/json")
        self.assertEqual(resp.status_code, 200)

        resp = self.client.get(url, {"user": user.username})
        self.assertEqual(resp.status_code, 200)

        reservation = Reservation.objects.create(date="2019-04-12 23:00", number_clients=3, user=cl, restaurant=r, comments="")
        url_update = reverse("update_reservation")
        data = {"id": reservation.id, "date": "2019-04-12 23:00", "number_clients": 3}
        data = json.dumps(data)
        resp = self.client.post(url_update, data=data.encode(), content_type="application/json")
        self.assertEqual(resp.status_code, 200)

        url_remove = reverse("remove_reservation")
        resp = self.client.get(url_remove, {"id": reservation.id})
        self.assertEqual(resp.status_code, 200)

    def test_reviews(self):
        url = reverse("review")
        r = Restaurant.objects.create(name="Txacoli", location="Artxanda", speciality="Grill"
                                      , menu="", score=3.0, capacity=40, schedule="12:00/15:00", type="asador")
        Review.objects.create(restaurant=r, score=4.0, comments="", date="2019-04-12 23:00")
        resp = self.client.get(url, {"restaurant": r.name})
        self.assertEqual(resp.status_code, 200)




