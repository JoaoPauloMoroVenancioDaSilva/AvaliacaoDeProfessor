<template>
  <div>
    <h2>Meu Perfil - Aluno</h2>

    <h3>Minhas Notas</h3>
    <div v-if="minhasNotas.length === 0">Nenhuma nota recebida ainda.</div>
    <div v-for="nota in minhasNotas" :key="nota.id" class="card-nota">
      <p><strong>Nota:</strong> {{ nota.nota }}</p>
      <p><strong>Comentário:</strong> {{ nota.comentario }}</p>
    </div>

    <h3>Solicitar Novo Professor</h3>
    <select v-model="profSelecionado">
      <option value="" disabled>Selecione um professor...</option>
      <option v-for="p in professores" :value="p.id" :key="p.id">
        {{ p.nome }}
      </option>
    </select>
    <button @click="solicitarVinculo">Enviar Solicitação</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from '../services/api';

const alunoId = 1;
const minhasNotas = ref([]);
const professores = ref([]);

const profSelecionado = ref('');

const carregarDados = async () => {
  //tratamento independente para buscar as notas
  try {
    const notasRes = await api.get(`/avaliacoes/aluno/${alunoId}`);
    minhasNotas.value = notasRes.data;
  } catch (error) {
    console.error("Erro ao buscar notas:", error);
  }

  //tratamento independente para buscar os professores
  try {
    const profsRes = await api.get('/professores');
    professores.value = profsRes.data;
  } catch (error) {
    console.error("Erro ao buscar professores:", error);
  }
};

const solicitarVinculo = async () => {
  if (!profSelecionado.value) {
    alert('Por favor, selecione um professor primeiro.');
    return;
  }

  try {
    await api.post(`/solicitacoes/requisitar/${alunoId}/${profSelecionado.value}`);
    alert('Solicitação enviada ao coordenador!');

    //reeseta o select após o envio
    profSelecionado.value = '';
  } catch (error) {
    console.error("Erro ao solicitar vínculo:", error);
    alert('Erro: Você provavelmente já tem uma solicitação pendente para este professor.');
  }
};

onMounted(() => {
  carregarDados();
});
</script>
