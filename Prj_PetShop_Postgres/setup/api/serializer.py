from rest_framework import serializers
from .models import Dono, Pet

class PetSerializer(serializers.ModelSerializer):
    class Meta:
        model = Pet
        fields = '__all__'

class DonoSerializer(serializers.ModelSerializer):
    pets = PetSerializer(many=True, read_only=True)

    class Meta:
        model = Dono
        #fields = ['id', 'nome', 'data_nascimento', 'cpf', 'pets']
        fields = '__all__'