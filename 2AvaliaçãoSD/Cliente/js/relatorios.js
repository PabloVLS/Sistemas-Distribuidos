const URL_BASE = 'http://localhost:8080/gestor';

document.addEventListener('DOMContentLoaded', carregarRelatorio);

async function carregarRelatorio() {
    try {
        const response = await fetch(`${URL_BASE}/relatorio`);
        const dados = await response.json();
        
        atualizarEstatisticas(dados);
        exibirRelatorio(dados);
    } catch (error) {
        console.error('Erro ao carregar relatório:', error);
        document.getElementById('tabelaRelatorio').innerHTML = `
            <div class="empty-state">
                <i class="bi bi-exclamation-triangle"></i>
                <p>Erro ao carregar relatório</p>
            </div>
        `;
    }
}

function atualizarEstatisticas(dados) {
    const pessoasUnicas = [...new Set(dados.map(item => item.pessoaId))];
    const veiculosUnicos = [...new Set(dados.map(item => item.veiculoId).filter(id => id))];
    const vinculos = dados.filter(item => item.veiculoId).length;
    
    document.getElementById('totalPessoas').textContent = pessoasUnicas.length;
    document.getElementById('totalVeiculos').textContent = veiculosUnicos.length;
    document.getElementById('totalVinculos').textContent = vinculos;
}

function exibirRelatorio(dados) {
    const tbody = document.getElementById('tabelaRelatorio');
    
    if (dados.length === 0) {
        tbody.innerHTML = `
            <div class="empty-state">
                <i class="bi bi-inbox"></i>
                <p>Nenhum dado encontrado</p>
            </div>
        `;
        return;
    }

    tbody.innerHTML = dados.map(item => `
        <div class="table-row">
            <div class="table-cell" data-label="ID">${item.pessoaId}</div>
            <div class="table-cell" data-label="Nome">${item.pessoaNome}</div>
            <div class="table-cell" data-label="CPF">${item.pessoaCpf}</div>
            <div class="table-cell ${!item.veiculoModelo ? 'empty' : ''}" data-label="Veículo">
                ${item.veiculoModelo || 'Sem veículo'}
            </div>
            <div class="table-cell ${!item.veiculoAno ? 'empty' : ''}" data-label="Ano">
                ${item.veiculoAno || '-'}
            </div>
            <div class="table-cell ${!item.veiculoPlaca ? 'empty' : ''}" data-label="Placa">
                ${item.veiculoPlaca || '-'}
            </div>
            <div class="table-cell" data-label="Ações">
                <div class="actions">
                    <button class="action-btn" onclick="excluirPessoa(${item.pessoaId})" title="Excluir pessoa">
                        <i class="bi bi-trash"></i>
                    </button>
                </div>
            </div>
        </div>
    `).join('');
}

async function excluirPessoa(id) {
    if (!confirm('Excluir esta pessoa e todos os seus vínculos?')) return;

    try {
        const response = await fetch(`${URL_BASE}/pessoas/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            carregarRelatorio();
        } else {
            alert('Erro ao excluir pessoa');
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao conectar com o servidor');
    }
}