import { createRouter, createWebHistory } from 'vue-router'

//importa as telas
import LoginView from '../views/LoginView.vue'
import DashboardView from '../views/DashboardView.vue'
import ProfessoresView from '../views/ProfessoresView.vue'
import AlunosView from '../views/AlunosView.vue'
import PerfilAlunoView from '../views/PerfilAlunoView.vue'
import PerfilProfessorView from '../views/PerfilProfessorView.vue'
import AvaliacaoView from '../views/AvaliacaoView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: '/login' },
    { path: '/login', component: LoginView },
    { path: '/dashboard', component: DashboardView }, // Tela do Coordenador (Aprovar/Recusar)
    { path: '/gerenciar-professores', component: ProfessoresView },
    { path: '/gerenciar-alunos', component: AlunosView },
    { path: '/area-aluno', component: PerfilAlunoView },
    { path: '/area-professor', component: PerfilProfessorView },
    { path: '/avaliar', component: AvaliacaoView },
  ]
})

export default router
