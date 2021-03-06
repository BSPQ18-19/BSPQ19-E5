from django.conf.urls import url
from django.contrib import admin
from django.urls import include

from polls import views

urlpatterns = [
    url(r'^admin/', admin.site.urls, name="admin"),
    url(r'^register/', views.register_api, name="registration"),
    url(r'^login/', views.login_api, name="login"),
    url(r'^restaurants/', views.restaurants_api, name="restaurant"),
    url(r'^reservations/', views.reservations_api, name="reservation"),
    url(r'^reviews/', views.reviews_api, name="review"),
    url(r'^remove/reservations/', views.delete_reservation, name="remove_reservation"),
    url(r'^update/reservations/', views.update_reservation, name="update_reservation"),
    url(r'^favourite/', views.make_favourite, name="favourite"),
]
urlpatterns += [url(r'^silk/', include('silk.urls', namespace='silk'))]
