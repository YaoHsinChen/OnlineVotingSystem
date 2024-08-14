<template>
  <div id="app">
    <VotingItems />
    <VotingRecords />
  </div>
</template>

<script>
import VotingItems from './components/VotingItems.vue';
import VotingRecords from './components/VotingRecords.vue';

export default {
  components: {
    VotingItems,
    VotingRecords,
  },
};
</script>
