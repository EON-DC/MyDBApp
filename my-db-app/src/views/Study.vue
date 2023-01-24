<template>
  <div class="container-fluid" @keyup="selectNum">
    <div class="mb-3">
      <div class="text-center m-2"><h1>My Quiz</h1></div>
      <div class="d-flex justify-content-between mb-3">
        <button
          type="button"
          class="btn btn-outline-secondary"
          @click="getPreviousQuiz()"
        >
          이전
        </button>
        <span
          >현재 문제 {{ this.quizIndex + 1 }} / {{ this.quizList.length }}</span
        >
        <button type="button" class="btn btn-primary" @click="getNextQuiz()">
          다음
        </button>
      </div>
      <div class="alert alert-dark mx-1">
        <span class="fw-bolder"> 다음은 무엇입니까? </span>
        <br />
        &nbsp;&nbsp;&nbsp;{{ quiz?.concept }}
      </div>
      <div
        class="form-check mb-4"
        v-for="(selected, i) in quiz?.select"
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

      <div class="text-center" v-if="this.picked != null">
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
  components: { Question },

  data() {
    return {
      quizList: [],
      quizIndex: 0,
      picked: null,
      haveSolvedCount: 0
    }
  },
  computed: {
    quiz() {
      return this.quizList[this.quizIndex]
    }
  },
  methods: {
    getNextQuiz() {
      this.picked = null
      if (this.quizList.length <= this.quizIndex + 1) {
        this.getQuiz()
      }
      this.quizIndex++
    },
    getPreviousQuiz() {
      this.picked = null
      if (this.quizIndex >= 1) {
        this.quizIndex--
      }
    },
    getQuiz() {
      this.picked = null
      this.solvedCountUpdate(this.haveSolvedCount++)
      fetch('http://localhost:8085/api/words/quiz')
        .then((response) => response.json())
        .then((response) => {
          this.quizList.push(response)
        })
    },
    solvedCountUpdate(e) {
      this.$emit('todayCountChange', e)
    },
    isCorrect() {
      if (this.picked == this.quiz.answerIndex) {
        return true
      }
      return false
    },
    selectNum(selectedNum) {
      switch (selectedNum.key) {
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
          this.picked = selectedNum.key - 1
          break
        case 'Enter':
        case ']':
          this.getNextQuiz()
          break
        case '[':
          this.getPreviousQuiz()
          break
      }
    }
  },
  created() {
    this.getQuiz()
  }
}
</script>
