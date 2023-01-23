<template>
  <div class="container">
    <div>
      <h1>My saved words</h1>
      <div class="card m-2" v-for="(word, i) in this.words" :key="i">
        <h5 class="card-title m-3">{{ i + 1 }}. {{ word.concept }}</h5>
        <h6 class="card-subtitle mx-3 text-muted text-end">
          {{ word.category }}
        </h6>
        <p class="card-text m-3" style="text-indent: 1rem">
          {{ word.meaning }}
        </p>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'study',
  components: {},
  data() {
    return {
      words: []
    }
  },
  methods: {
    getWordList() {
      fetch('http://localhost:8085/api/words')
        .then((response) => response.json())
        .then((response) => {
          this.words = response
        })
    }
  },
  mounted() {
    console.log('home called')
    this.getWordList()
  }
}
</script>
