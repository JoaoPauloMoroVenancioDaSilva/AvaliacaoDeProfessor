<template>
  <div class="container">
    <h2>Gerenciamento de Alunos</h2>
    <form @submit.prevent="salvarAluno" class="form-cadastro">
      <input v-model="aluno.nome" placeholder="Nome" required />
      <input v-model="aluno.email" type="email" placeholder="Email" required />
      <input v-model="aluno.matricula" placeholder="Matrícula" required />
      <input v-model="aluno.senha" type="password" placeholder="Senha" required />
      <button type="submit">Cadastrar Aluno</button>
    </form>

    <ul>
      <li v-for="a in alunos" :key="a.id">
        {{ a.nome }} ({{ a.matricula }})
        <button @click="remover(a.id)">Excluir</button>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from '../services/api';

const alunos = ref([]);
const coordenadorId = 1;
const aluno = ref({ nome: '', email: '', matricula: '', senha: '', coordenadorId });

const listar = async () => {
  const res = await api.get('/alunos'); 
  alunos.value = res.data;
};

const salvarAluno = async () => {
  await api.post('/alunos', aluno.value);
  listar();
};

const remover = async (id) => {
  await api.delete(`/alunos/${id}/${coordenadorId}`);
  listar();
};

onMounted(listar);
</script>
