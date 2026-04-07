<template>
  <div class="login-container">
    <h2>Sistema de Avaliação</h2>
    <form @submit.prevent="fazerLogin">
      <div>
        <label>Email:</label>
        <input type="email" v-model="email" required />
      </div>
      <div>
        <label>Senha:</label>
        <input type="password" v-model="senha" required />
      </div>
      <button type="submit">Entrar</button>
    </form>
    <p v-if="erro" class="error">{{ erro }}</p>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import api from '../services/api';

const email = ref('');
const senha = ref('');
const erro = ref('');
const router = useRouter();

const fazerLogin = async () => {
  try {
    const response = await api.post('/login', {
      email: email.value,
      senha: senha.value
    });

    //retorno do token da API
    localStorage.setItem('token', response.data.token);

    //redirecionamento para o painel principal após o login
    router.push('/dashboard');
  } catch {
    erro.value = 'Credenciais inválidas. Tente novamente.';
  }
};
</script>

<style scoped>
.error { color: red; }
/* Adicione seu CSS aqui */
</style>
