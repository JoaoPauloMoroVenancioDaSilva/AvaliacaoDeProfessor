<template>
  <div id="app">
    <header v-if="mostrarMenu">
      <nav>
        <RouterLink to="/dashboard">Painel Coordenador</RouterLink> |
        <RouterLink to="/gerenciar-professores">Professores</RouterLink> |
        <RouterLink to="/gerenciar-alunos">Alunos</RouterLink> |
        <RouterLink to="/area-aluno">Área do Aluno</RouterLink> |
        <RouterLink to="/area-professor">Área do Professor</RouterLink> |
        <RouterLink to="/avaliar">Nova Avaliação</RouterLink> |
        <a href="#" @click.prevent="sair">Sair</a>
      </nav>
    </header>

    <main>
      <RouterView />
    </main>
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useRouter, useRoute } from 'vue-router';

const router = useRouter();
const route = useRoute();

//oculta o menu se estiver na tela de login
const mostrarMenu = computed(() => route.path !== '/login');

const sair = () => {
  localStorage.removeItem('token');
  router.push('/login');
};
</script>

<style scoped>
header {
  background-color: #f4f4f9;
  padding: 1rem;
  margin-bottom: 2rem;
  border-bottom: 1px solid #ccc;
}
nav a {
  margin-right: 10px;
  text-decoration: none;
  color: #333;
  font-weight: bold;
}
nav a.router-link-exact-active {
  color: #42b983; /*cor verde padrão*/
}
main {
  padding: 0 2rem;
}
</style>
