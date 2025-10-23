from django.db import models
from django.conf import settings
from django_mongodb_backend.fields import EmbeddedModelField, ObjectIdAutoField
from django_mongodb_backend.models import EmbeddedModel

# Create your models here.
class Pet(EmbeddedModel):
    nome = models.CharField(max_length=100)
    raca= models.CharField(max_length=100)
    especie = models.CharField(max_length=100)
    data_nascimento = models.DateField()
    porte = models.CharField(max_length=100)
    cor = models.CharField(max_length=100)

class Dono(models.Model):
    id = ObjectIdAutoField(primary_key=True)
    nome = models.CharField(max_length=100)
    data_nascimento = models.DateField()
    cpf = models.CharField(max_length=11)
    pets = EmbeddedModelField(Pet, null=True, blank=True)

    class Meta:
        db_table = 'donos'
        managed = False
    
    def __str__(self):
        return f"{self.nome} ({self.cpf})"
    
