<template>
  <div class="container-fluid">
    <div class="mb-3">
      <div class="text-center m-2"><h1>My Quiz</h1></div>
      <div class="d-flex justify-content-end mb-3">
        <button type="button" class="btn btn-primary" @click="getQuiz()">
          다음
        </button>
      </div>
      <div class="alert alert-dark mx-1">
        <span class="fw-bolder"> 다음은 무엇입니까? </span>
        <br />
        &nbsp;&nbsp;&nbsp;{{ quiz.concept }}
      </div>
      <div
        class="form-check mb-4"
        v-for="(selected, i) in quiz.select"
        :key="i"
      >
        <input
          class="p-2 form-check-input"
          type="radio"
          :id="i"
          :value="i"
          v-model="picked"
        />
        <label class="form-check-label" :for="i">{{ selected.meaning }}</label>
        <Question :selected="selected" />
      </div>

      <div class="text-center" v-if="this.picked !== null">
        <p class="badge bg-success text-wrap" v-show="isCorrect()">정답</p>
        <p class="badge bg-warning text-wrap" v-show="!isCorrect()">오답</p>
      </div>
    </div>
  </div>
</template>
<script>
import Question from '../components/Question'

export default {
  name: 'StudyView',
  components: {
    Question
  },

  data() {
    return {
      quiz: {},
      picked: null,
      haveSolvedCount: 0
    }
  },
  methods: {
    getQuiz() {
      this.picked = null
      this.haveSolvedCount++
      this.solvedCountUpdate(this.haveSolvedCount)
      fetch('http://localhost:8085/api/words/quiz')
        .then((response) => response.json())
        .then((response) => {
          this.quiz = response
        })
    },
    solvedCountUpdate(e) {
      console.log('#Study. count ' + e)
      this.$emit('todayCountChange', e)
    },
    isCorrect() {
      if (this.picked == this.quiz.answerIndex) {
        return true
      }
      return false
    }
  },
  mounted() {
    console.log('study called')
    this.getQuiz()
  }
}
</script>
