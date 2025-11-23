const URL_BASE = 'http://localhost:8080/gestor';
let pessoaSelecionada = null;

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('formPessoa').addEventListener('submit', salvarPessoa);
    carregarPessoas();
});

async function salvarPessoa(e) {
    e.preventDefault();
    
    const pessoa = {
        nome: document.getElementById('nome').value,
        cpf: document.getElementById('cpf').value
    };

    try {
        const response = await fetch(`${URL_BASE}/pessoas`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(pessoa)
        });

        if (response.ok) {
            document.getElementById('formPessoa').reset();
            carregarPessoas();
        } else {
            alert('Erro ao cadastrar pessoa');
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao conectar com o servidor');
    }
}

async function carregarPessoas() {
    try {
        const response = await fetch(`${URL_BASE}/pessoas`);
        const pessoas = await response.json();
        exibirPessoas(pessoas);
    } catch (error) {
        console.error('Erro ao carregar pessoas:', error);
        document.getElementById('listaPessoas').innerHTML = `
            <div class="empty-state">
                <i class="bi bi-exclamation-triangle"></i>
                <p>Erro ao carregar pessoas</p>
            </div>
        `;
    }
}

function exibirPessoas(pessoas) {
    const lista = document.getElementById('listaPessoas');
    const contador = document.getElementById('contadorPessoas');
    
    contador.textContent = pessoas.length;

    if (pessoas.length === 0) {
        lista.innerHTML = `
            <div class="empty-state">
                <i class="bi bi-inbox"></i>
                <p>Nenhuma pessoa cadastrada</p>
            </div>
        `;
        return;
    }

    lista.innerHTML = pessoas.map(pessoa => `
        <div class="person-item">
            <div class="person-detail">#${pessoa.id}</div>
            <div class="person-name">${pessoa.nome}</div>
            <div class="person-detail">${pessoa.cpf}</div>
            <div class="person-actions">
                <button class="action-btn" onclick="abrirModalEditarPessoa(${pessoa.id}, '${pessoa.nome}', '${pessoa.cpf}')">
                    <i class="bi bi-pencil"></i>
                </button>
                <button class="action-btn success" onclick="abrirModalVinculo(${pessoa.id}, '${pessoa.nome}')">
                    <i class="bi bi-link-45deg"></i>
                </button>
                <button class="action-btn danger" onclick="excluirPessoa(${pessoa.id})">
                    <i class="bi bi-trash"></i>
                </button>
            </div>
        </div>
    `).join('');
}

async function excluirPessoa(id) {
    if (!confirm('Excluir esta pessoa?')) return;

    try {
        const response = await fetch(`${URL_BASE}/pessoas/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            carregarPessoas();
        } else {
            alert('Erro ao excluir pessoa');
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao conectar com o servidor');
    }
}

function abrirModalEditarPessoa(id, nome, cpf) {
    document.getElementById('idPessoaEditar').value = id;
    document.getElementById('nomeEditar').value = nome;
    document.getElementById('cpfEditar').value = cpf;
    document.getElementById('modalEditarPessoa').classList.add('show');
}

function fecharModalPessoa() {
    document.getElementById('modalEditarPessoa').classList.remove('show');
}

async function salvarEdicaoPessoa() {
    const id = document.getElementById('idPessoaEditar').value;
    const pessoa = {
        nome: document.getElementById('nomeEditar').value,
        cpf: document.getElementById('cpfEditar').value
    };

    try {
        const response = await fetch(`${URL_BASE}/pessoas/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(pessoa)
        });

        if (response.ok) {
            fecharModalPessoa();
            carregarPessoas();
        } else {
            alert('Erro ao atualizar pessoa');
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao conectar com o servidor');
    }
}

async function abrirModalVinculo(idPessoa, nomePessoa) {
    pessoaSelecionada = idPessoa;
    document.getElementById('nomePessoaVinculo').textContent = nomePessoa;
    
    try {
        const response = await fetch(`${URL_BASE}/veiculos`);
        const veiculos = await response.json();
        
        const select = document.getElementById('selectVeiculo');
        select.innerHTML = '<option value="">Selecione um veículo</option>';
        
        veiculos.forEach(veiculo => {
            const option = document.createElement('option');
            option.value = veiculo.id;
            option.textContent = `${veiculo.modelo} - ${veiculo.placa}`;
            select.appendChild(option);
        });
        
        document.getElementById('modalVincular').classList.add('show');
    } catch (error) {
        console.error('Erro ao carregar veículos:', error);
        alert('Erro ao carregar veículos');
    }
}

function fecharModalVinculo() {
    document.getElementById('modalVincular').classList.remove('show');
}

async function confirmarVinculo() {
    const idVeiculo = document.getElementById('selectVeiculo').value;
    
    if (!idVeiculo) {
        alert('Selecione um veículo');
        return;
    }

    try {
        const response = await fetch(`${URL_BASE}/vincular/${pessoaSelecionada}/${idVeiculo}`, {
            method: 'POST'
        });

        if (response.ok) {
            fecharModalVinculo();
            alert('Veículo vinculado com sucesso!');
        } else {
            alert('Erro ao vincular veículo');
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao conectar com o servidor');
    }
}