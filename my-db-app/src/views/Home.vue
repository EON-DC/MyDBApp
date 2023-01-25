<template>
  <div class="container">
    <div>
      <h1 class="text-center my-2">My saved words</h1>
      <SaveModal />
      <div class="card mb-2" v-for="(word, i) in this.words" :key="i">
        <div class="d-flex justify-content-between">
          <span class="h2 card-title mx-3 mt-3"
            >{{ i + 1 }}. {{ word.concept }}</span
          >
          <EditModal :word="word" />
        </div>
        <blockquote v-if="word.fullName != 'null'" class="mx-4">
          &nbsp;&nbsp;&nbsp;{{ word.fullName }}
        </blockquote>

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
import EditModal from '../components/EditModal.vue'
import SaveModal from '../components/SaveModal.vue'
export default {
  name: 'study',
  components: { EditModal, SaveModal },
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
