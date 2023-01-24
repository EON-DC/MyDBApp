<template>
  <span class="mx-2">
    <img
      src="../assets/question.svg"
      alt=""
      data-bs-toggle="collapse"
      :href="'#collapse' + selected.id"
      data-placement="bottom"
      role="button"
      aria-expanded="false"
      :aria-controls="'collapse' + selected.id"
    />
  </span>
  <div>
    <div class="collapse" :id="'collapse' + selected.id">
      <div class="card card-body">
        개념 : {{ selected.concept }} <br />카테고리 : {{ selected.category }}
        <br />
        <span v-if="selected.fullName != 'null'">{{ selected.fullName }}</span>
        <a
          class="text-end"
          :href="'http://localhost:8080/words/edit?id=' + selected.id"
          >edit</a
        >
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: ['selected'], // concept, answerIndex, select
  data() {
    return {
      picked: null
    }
  },
  methods: {}
}
</script>
