from app.database import mongo

class Dono:
    @staticmethod
    def to_dict(dono):
        # Corrige referência à chave 'pets' (opcional: garante que sempre exista)
        dono['pets'] = dono.get('pets', [])

        if dono.get("_id", "") == "":
            return {
                "nome": dono["nome"],
                "data_nascimento": dono.get("data_nascimento", ""),
                "cpf": dono["cpf"],
                "pets": dono["pets"],
            }
        else:
            return {
                "id": str(dono["_id"]),
                "nome": dono["nome"],
                "data_nascimento": dono.get("data_nascimento", ""),
                "cpf": dono["cpf"],
                "pets": dono["pets"],
            }
