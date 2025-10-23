from django.urls import path
from .views import get_donos, create_dono, dono_detail

urlpatterns = [
    path('donos/', get_donos, name='get_donos'),
    path('donos/create', create_dono, name='create_dono'),
    path('donos/<str:pk>/', dono_detail, name='dono_detail'),
]