from django.conf.urls import url
from django.contrib import admin
from polls import views

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^register/', views.register_api),
    url(r'^login/', views.login_api),
    url(r'^restaurants/', views.restaurants_api),
    url(r'^reservations/', views.reservations_api),
]
