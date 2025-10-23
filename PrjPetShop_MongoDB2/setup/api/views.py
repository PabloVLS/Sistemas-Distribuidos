from django.shortcuts import render
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status
from .models import Dono, Pet
from .serializer import DonoSerializer


# GET de todos os donos
@api_view(['GET'])
def get_donos(request):
    donos = Dono.objects.all()
    serializer = DonoSerializer(donos, many=True)
    return Response(serializer.data)

# POST para criar um dono
@api_view(['POST'])
def create_dono(request):
    serializer = DonoSerializer(data=request.data)
    if serializer.is_valid():
        serializer.save()
        return Response(serializer.data, status=status.HTTP_201_CREATED)
    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

@api_view(['GET', 'PUT', 'DELETE'])
def dono_detail(request, pk):
    try:
        dono = Dono.objects.get(pk=pk)
    except Dono.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = DonoSerializer(dono)
        return Response(serializer.data)

    elif request.method == 'PUT':
        serializer = DonoSerializer(dono, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif request.method == 'DELETE':
        dono.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)