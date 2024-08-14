<template>
  <div class="container">
    <h1>投票紀錄</h1>
    
    <!-- 显示投票紀錄 -->
    <ul class="record-list">
      <li v-for="record in records" :key="record.recordId" class="record-item">
        <span class="voter">{{ record.voter }}</span>
        <span class="separator">:</span>
        <span class="item-name">{{ record.itemId }}</span>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      records: [],
    };
  },
  created() {
    this.fetchRecords();
  },
  methods: {
    async fetchRecords() {
      try {
        const response = await axios.get('http://localhost:8080/api/voting-records');
        console.log('Response data:', response.data); 
        this.records = response.data;
      } catch (error) {
        console.error('Error fetching records:', error);
      }
    },
  },
};
</script>

<style scoped>
.container {
  width: 80%;
  margin: 0 auto;
  text-align: center; /* Center all text in the container */
  font-family: Arial, sans-serif;
}

h1 {
  margin-bottom: 30px;
  font-size: 2em;
  color: #333;
}

.record-list {
  list-style-type: none;
  padding: 0;
}

.record-item {
  margin: 20px 0;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f9f9f9;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.2em;
  color: #333;
  transition: background-color 0.3s, box-shadow 0.3s;
  max-width: 600px; /* Set a max-width to prevent the box from being too wide */
  margin-left: auto; /* Center horizontally */
  margin-right: auto; /* Center horizontally */
}

.record-item:hover {
  background-color: #eaf0f6;
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
}

.voter {
  font-weight: bold;
}

.separator {
  margin: 0 10px;
}

.item-name {
  font-style: italic;
  color: #555;
}
</style>
