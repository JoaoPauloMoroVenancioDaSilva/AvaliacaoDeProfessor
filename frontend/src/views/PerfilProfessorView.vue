<template>
  <div>
    <h2>Painel do Professor</h2>
    <h3>Avaliações Recebidas dos Alunos</h3>

    <div v-if="avaliacoes.length === 0">Nenhuma avaliação recebida ainda.</div>

    <div v-for="av in avaliacoes" :key="av.id" class="feedback-item">
      <strong>Nota: {{ av.nota }} / 5</strong>
      <p>"{{ av.comentario }}"</p>
      <small>Data: {{ new Date(av.dataAvaliacao).toLocaleDateString() }}</small>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from '../services/api';

const professorId = 1; 
const avaliacoes = ref([]);

const carregarFeedback = async () => {
  try {
    const res = await api.get(`/avaliacoes/professor/${professorId}`); //
    avaliacoes.value = res.data;
  } catch (e) {
    console.error("Erro ao carregar feedbacks", e);
  }
};

onMounted(carregarFeedback);
</script>
