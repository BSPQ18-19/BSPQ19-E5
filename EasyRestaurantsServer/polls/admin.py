from django.contrib import admin
from .models import Client, Restaurant, Reservation

class ClientAdmin(admin.ModelAdmin):
    list_display = ('get_name',)
    search_fields = ['get_name']
    def get_name(self, obj):
        return obj.user.username
    get_name.short_description = 'User'


class RestaurantAdmin(admin.ModelAdmin):
    list_display = ('name', 'location' , 'score')
    list_filter = ['name']
    search_fields = ['name']


class ReservationAdmin(admin.ModelAdmin):
    list_display = ('get_name', 'date', 'number_clients' )
    list_filter = ['date']
    search_fields = ['get_name']
    def get_name(self, obj):
        return obj.restaurant.name
    get_name.short_description = 'Restaurant'

admin.site.register(Client, ClientAdmin)
admin.site.register(Restaurant, RestaurantAdmin)
admin.site.register(Reservation, ReservationAdmin)