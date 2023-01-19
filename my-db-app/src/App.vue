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
    <div></div>
    <router-view :nameOfChild="name" :words="data.words" :quiz="data.quiz" />
  </div>
</template>
<script>
import { reactive, onMounted } from 'vue'
const date = new Date()
export default {
  name: '',
  components: {},
  data() {
    return {
      obj: {
        firstName: 'kim',
        lastName: 'chulSu',
        count: 0
      },
      isButtonDisabled: false,
      name: 'park',
      author: {
        name: 'John Doe',
        books: [
          'Vue 2 - Advanced Guide',
          'Vue 3 - Basic Guide',
          'Vue 4 - The Mystery'
        ]
      },
      dateTime: {
        hours: date.getHours(),
        minutes: date.getMinutes(),
        seconds: date.getSeconds()
      },
      timer: undefined
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
  computed: {
    // a computed getter
    publishedBooksMessage() {
      // `this` points to the component instance
      return this.author.books.length > 0 ? 'Yes' : 'No'
    }
  },
  methods: {
    countClick() {
      this.obj.count++
      this.author.books = this.author.books.slice(
        0,
        this.author.books.length - 1
      )
    },
    resetCount() {
      this.obj.count = 0
    },
    setDateTime() {
      const date = new Date()
      this.dateTime = {
        hours: date.getHours(),
        minutes: date.getMinutes(),
        seconds: date.getSeconds()
      }
    }
  },
  beforeMount() {
    this.timer = setInterval(this.setDateTime, 1000)
  },
  beforeUnmount() {
    clearInterval(this.timer)
  }
}
</script>
