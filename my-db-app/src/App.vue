<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
        <router-link to="/">
          <span>Home</span>
        </router-link>
        <router-link to="/study">
          <span>Study</span>
        </router-link>
      </div>
    </nav>
    <router-view :nameOfChild="name" :words="data.words" :quiz="data.quiz" />
  </div>
</template>
<script>
import { reactive, onMounted } from 'vue'

export default {
  name: '',
  components: {},
  data() {
    return {
      name: 'park'
    }
  },
  setup() {
    const data = reactive({
      words: [],
      quiz: ''
    })
    const getWordList = () => {
      fetch('http://localhost:8085/api/words')
        .then((response) => response.json())
        .then((response) => {
          data.words = response
        })
    }
    const getQuiz = () => {
      fetch('http://localhost:8085/api/words/quiz')
        .then((response) => response.json())
        .then((response) => {
          data.quiz = response
        })
    }
    onMounted(() => {
      getWordList(), getQuiz()
      console.log(data)
    })
    return {
      data: data,
      getWordList: getWordList,
      getQuiz: getQuiz
    }
  },

  created() {},
  mounted() {},
  unmounted() {},
  methods: {}
}
</script>
