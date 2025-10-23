from rest_framework import serializers
from .models import Dono, Pet

# Serializer para Pet
class PetSerializer(serializers.ModelSerializer):
    class Meta:
        model = Pet
        fields = ['nome', 'raca', 'especie', 'data_nascimento', 'porte', 'cor']

# Serializer para Dono
class DonoSerializer(serializers.ModelSerializer):
    id = serializers.SerializerMethodField()
    pets = PetSerializer(many=True, allow_null=True)

    class Meta:
        model = Dono
        fields = ['id', 'nome', 'data_nascimento', 'cpf', 'pets']

    def get_id(self, obj):
        # Converte o objectId em string para que o JSON possa lidar com ele
        return str(obj.id) if obj.id else None

    def create(self, validated_data):
        # Extrai os dados dos pets
        pet_data = validated_data.pop('pets', None)
        dono = Dono.objects.create(**validated_data)

        # Cria os pets relacionados, se houver
        if pet_data:
            dono.pets = Pet(**pet_data)
        
        dono.save()
        return dono

    def update(self, instance, validated_data):
        # Extrai dados dos pets
        pet_data = validated_data.pop('pets', None)

        # Atualiza os campos do Dono
        for attr, value in validated_data.items():
            setattr(instance, attr, value)
        instance.save()

        if pet_data is not None:
            # Remove os pets antigos relacionados ao dono
            instance.pets.all().delete()

            # Cria os novos pets
            for pet in pet_data:
                Pet.objects.create(dono=instance, **pet)

        return instance

