from flask import Blueprint, request, jsonify
from bson.objectid import ObjectId
from app.database import mongo
from app.models import Dono

api_bp = Blueprint("api", __name__)

# --- Criar um novo dono ---
@api_bp.route("/donos", methods=["POST"])
def create_dono():
    data = request.json
    dono = Dono.to_dict(data)  # type: ignore

    result = mongo.db.donos.insert_one(dono)  # type: ignore
    return jsonify({"id": str(result.inserted_id), **dono}), 201


# --- Listar todos os donos ---
@api_bp.route("/donos", methods=["GET"])
def get_donos():
    donos = mongo.db.donos.find()  # type: ignore
    return jsonify([Dono.to_dict(dono) for dono in donos]), 200


# --- Buscar dono por ID ---
@api_bp.route("/donos/<string:dono_id>", methods=["GET"])
def get_dono(dono_id):
    try:
        dono = mongo.db.donos.find_one({"_id": ObjectId(dono_id)})  # type: ignore
    except:
        return jsonify({"error": "ID inválido"}), 400

    if dono:
        return jsonify(Dono.to_dict(dono)), 200

    return jsonify({"error": "Dono não encontrado"}), 404


# --- Atualizar dono ---
@api_bp.route("/donos/<string:dono_id>", methods=["PUT"])
def update_dono(dono_id):
    data = request.json
    update_data = {"$set": data}

    try:
        result = mongo.db.donos.update_one({"_id": ObjectId(dono_id)}, update_data)  # type: ignore
    except:
        return jsonify({"error": "ID inválido"}), 400

    if result.matched_count:
        updated_dono = mongo.db.donos.find_one({"_id": ObjectId(dono_id)})  # type: ignore
        return jsonify(Dono.to_dict(updated_dono)), 200

    return jsonify({"error": "Dono não encontrado"}), 404


# --- Deletar dono ---
@api_bp.route("/donos/<string:dono_id>", methods=["DELETE"])
def delete_dono(dono_id):
    try:
        result = mongo.db.donos.delete_one({"_id": ObjectId(dono_id)})  # type: ignore
    except:
        return jsonify({"error": "ID inválido"}), 400

    if result.deleted_count:
        return jsonify({"message": "Dono deletado com sucesso"}), 200

    return jsonify({"error": "Dono não encontrado"}), 404