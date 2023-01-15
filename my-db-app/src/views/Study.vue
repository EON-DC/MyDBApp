<template>
  <div class="container">
    <div>
      <h1>My Quiz</h1>
    </div>
  </div>
</template>
<script>
export default {
  name: 'StudyView',
  props: {
    quiz: {
      concept: String,
      answerIndex: Number,
      questionArray: Array
    }
  },
  data() {
    return {
      pickedNum: null,
      isAnswer: false,
      isClicked: false
    }
  },
  methods: {
    setIsClicked() {
      this.isClicked = true
    }
  }
}
</script>
