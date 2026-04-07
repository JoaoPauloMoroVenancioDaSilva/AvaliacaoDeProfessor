<template>
  <div class="container">
    <h2>Gerenciamento de Professores</h2>
    <form @submit.prevent="salvarProfessor" class="form-cadastro">
      <input v-model="novoProf.nome" placeholder="Nome" required />
      <input v-model="novoProf.email" type="email" placeholder="Email" required />
      <input v-model="novoProf.matricula" placeholder="Matrícula" required />
      <input v-model="novoProf.senha" type="password" placeholder="Senha" required />
      <button type="submit">Cadastrar Professor</button>
    </form>

    <table>
      <thead>
        <tr>
          <th>Nome</th>
          <th>Matrícula</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="prof in professores" :key="prof.id">
          <td>{{ prof.nome }}</td>
          <td>{{ prof.matricula }}</td>
          <td>
            <button @click="deletar(prof.id)">Remover</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from '../services/api';

const professores = ref([]);
const coordenadorId = 1; 


const novoProf = ref({
  nome: '',
  email: '',
  matricula: '',
  senha: '',
  coordenadorId: coordenadorId
});

const listar = async () => {
  try {
    const res = await api.get('/professores');
    professores.value = res.data;
  } catch (error) {
    console.error("Erro ao listar professores:", error);
  }
};

const salvarProfessor = async () => {
  try {
    await api.post('/professores', novoProf.value);
    alert('Professor cadastrado com sucesso!');

    //limpa os imputs após o cadastro
    novoProf.value = { nome: '', email: '', matricula: '', senha: '', coordenadorId: coordenadorId };
    listar();
  } catch (error) {
    console.error("Erro ao cadastrar professor:", error);
    alert('Erro ao cadastrar.');
  }
};

const deletar = async (id) => {
  if (confirm('Deseja remover este professor?')) {
    try {
      await api.delete(`/professores/${id}/${coordenadorId}`);
      listar();
    } catch (error) {
      console.error("Erro ao deletar professor:", error);
      alert('Erro ao remover.');
    }
  }
};

onMounted(() => {
  listar();
});
</script>
