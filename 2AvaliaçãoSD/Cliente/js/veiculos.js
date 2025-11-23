const URL_BASE = 'http://localhost:8080/gestor';

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('formVeiculo').addEventListener('submit', salvarVeiculo);
    carregarVeiculos();
});

async function salvarVeiculo(e) {
    e.preventDefault();
    
    const veiculo = {
        modelo: document.getElementById('modelo').value,
        ano: parseInt(document.getElementById('ano').value),
        placa: document.getElementById('placa').value
    };

    try {
        const response = await fetch(`${URL_BASE}/veiculos`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(veiculo)
        });

        if (response.ok) {
            document.getElementById('formVeiculo').reset();
            carregarVeiculos();
        } else {
            alert('Erro ao cadastrar veículo');
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao conectar com o servidor');
    }
}

async function carregarVeiculos() {
    try {
        const response = await fetch(`${URL_BASE}/veiculos`);
        const veiculos = await response.json();
        exibirVeiculos(veiculos);
    } catch (error) {
        console.error('Erro ao carregar veículos:', error);
        document.getElementById('listaVeiculos').innerHTML = `
            <div class="empty-state">
                <i class="bi bi-exclamation-triangle"></i>
                <p>Erro ao carregar veículos</p>
            </div>
        `;
    }
}

function exibirVeiculos(veiculos) {
    const lista = document.getElementById('listaVeiculos');
    const contador = document.getElementById('contadorVeiculos');
    
    contador.textContent = veiculos.length;

    if (veiculos.length === 0) {
        lista.innerHTML = `
            <div class="empty-state">
                <i class="bi bi-inbox"></i>
                <p>Nenhum veículo cadastrado</p>
            </div>
        `;
        return;
    }

    lista.innerHTML = veiculos.map(veiculo => `
        <div class="vehicle-item">
            <div class="vehicle-model">${veiculo.modelo}</div>
            <div class="vehicle-detail">${veiculo.ano}</div>
            <div class="vehicle-detail">${veiculo.placa}</div>
            <div class="vehicle-actions">
                <button class="action-btn" onclick="abrirModalEditar(${veiculo.id}, '${veiculo.modelo}', ${veiculo.ano}, '${veiculo.placa}')">
                    <i class="bi bi-pencil"></i>
                </button>
                <button class="action-btn delete" onclick="excluirVeiculo(${veiculo.id})">
                    <i class="bi bi-trash"></i>
                </button>
            </div>
        </div>
    `).join('');
}

async function excluirVeiculo(id) {
    if (!confirm('Excluir este veículo?')) return;

    try {
        const response = await fetch(`${URL_BASE}/veiculos/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            carregarVeiculos();
        } else {
            alert('Erro ao excluir veículo');
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao conectar com o servidor');
    }
}

function abrirModalEditar(id, modelo, ano, placa) {
    document.getElementById('idVeiculoEditar').value = id;
    document.getElementById('modeloEditar').value = modelo;
    document.getElementById('anoEditar').value = ano;
    document.getElementById('placaEditar').value = placa;
    document.getElementById('modalEditar').classList.add('show');
}

function fecharModal() {
    document.getElementById('modalEditar').classList.remove('show');
}

async function salvarEdicaoVeiculo() {
    const id = document.getElementById('idVeiculoEditar').value;
    const veiculo = {
        modelo: document.getElementById('modeloEditar').value,
        ano: parseInt(document.getElementById('anoEditar').value),
        placa: document.getElementById('placaEditar').value
    };

    try {
        const response = await fetch(`${URL_BASE}/veiculos/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(veiculo)
        });

        if (response.ok) {
            fecharModal();
            carregarVeiculos();
        } else {
            alert('Erro ao atualizar veículo');
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao conectar com o servidor');
    }
}