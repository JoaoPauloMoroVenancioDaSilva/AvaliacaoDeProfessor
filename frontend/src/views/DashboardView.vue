<template>
  <div>
    <h2>Painel do Coordenador - Solicitações Pendentes</h2>

    <div v-if="carregando">Carregando solicitações...</div>

    <table v-else>
      <thead>
        <tr>
          <th>ID</th>
          <th>Aluno</th>
          <th>Professor</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="solicitacao in solicitacoes" :key="solicitacao.id">
          <td>{{ solicitacao.id }}</td>
          <td>{{ solicitacao.aluno.nome }}</td>
          <td>{{ solicitacao.professor.nome }}</td>
          <td>
            <button @click="aprovar(solicitacao.id)">Aprovar</button>
            <button @click="recusar(solicitacao.id)">Recusar</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from '../services/api';

const solicitacoes = ref([]);
const carregando = ref(true);


const coordenadorId = 1;

const carregarSolicitacoes = async () => {
  try {
    const response = await api.get('/solicitacoes');
    //filtra apenas as não aprovadas no front (ou pode criar um endpoint específico no back)
    solicitacoes.value = response.data.filter(s => !s.aprovada);
  } catch (error) {
    console.error("Erro ao buscar solicitações", error);
  } finally {
    carregando.value = false;
  }
};

const aprovar = async (solicitacaoId) => {
  try {
    await api.put(`/solicitacoes/aprovar/${solicitacaoId}/${coordenadorId}`);
    alert('Solicitação aprovada!');
    carregarSolicitacoes();
  } catch {
    alert('Erro ao aprovar.');
  }
};

const recusar = async (solicitacaoId) => {
  try {
    await api.delete(`/solicitacoes/recusar/${solicitacaoId}/${coordenadorId}`);
    alert('Solicitação recusada!');
    carregarSolicitacoes();
  } catch {
    alert('Erro ao recusar.');
  }
};

onMounted(() => {
  carregarSolicitacoes();
});
</script>
