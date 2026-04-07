<template>
  <div>
    <h2>Nova Avaliação</h2>
    <form @submit.prevent="enviarAvaliacao">
      <div>
        <label>Tipo de Avaliador:</label>
        <select v-model="avaliacao.tipoAvaliador">
          <option value="ALUNO">Sou Aluno</option>
          <option value="PROFESSOR">Sou Professor</option>
        </select>
      </div>

      <div>
        <label>Nota (1 a 5):</label>
        <input type="number" min="1" max="5" v-model="avaliacao.nota" required />
      </div>

      <div>
        <label>Comentário:</label>
        <textarea v-model="avaliacao.comentario" required></textarea>
      </div>

      <button type="submit">Enviar Avaliação</button>
    </form>
  </div>
</template>

<script setup>
import { reactive } from 'vue';
import api from '../services/api';


const avaliacao = reactive({
  nota: null,
  comentario: '',
  avaliadorId: 1, //substituir pelo ID do usuário logado
  avaliadoId: 2,  //substituir pelo ID do alvo selecionado em uma tela anterior
  tipoAvaliador: 'ALUNO'
});

const enviarAvaliacao = async () => {
  try {
    await api.post('/avaliacoes', avaliacao);
    alert('Avaliação registrada com sucesso!');
    //limpar form ou redirecionar
  } catch (error) {
    alert('Erro ao registrar avaliação.');
    console.error(error);
  }
};
</script>
