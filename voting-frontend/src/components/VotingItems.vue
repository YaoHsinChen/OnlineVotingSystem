<template>
  <div class="container">
    <h1>投票項列表</h1>
    
    <!-- 显示投票项及其票数 -->
    <ul class="item-list">
      <li v-for="item in items" :key="item.id" class="item">
        <div class="item-content">
          <span class="item-name">{{ item.name }}</span>
          <span class="item-vote-count">票數: {{ item.voteCount }}</span>
        </div>
        <div class="item-actions">
          <label class="vote-label">
            <input type="checkbox" :value="item.id" v-model="selectedItems" class="vote-checkbox" />
            <span class="vote-description">勾選後提交投票</span>
          </label>
          <button @click="deleteItem(item.id)" class="delete-button">刪除投票項</button>
        </div>
      </li>
    </ul>
    
    <!-- 投票功能 -->
    <div class="vote-section">
     <div class="user-name-section">
      <input v-model="userName" placeholder="輸入使用者名稱進行投票" class="user-name-input" />
      </div>
      <button @click="submitVotes" class="submit-votes-button">提交投票</button>
    </div>
    
    <!-- 原有功能保持不变 -->
    <div class="add-item-section">
      <input v-model="newItemName" placeholder="輸入投票項" class="new-item-input" />
      <button @click="addItem" class="add-item-button">新增投票项</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      items: [],         // 存储所有投票项
      newItemName: '',   // 存储新投票项的名称
      selectedItems: [],  // 存储用户选择的投票项 ID
      userName: ''       // 存儲用戶名稱
    };
  },
  created() {
    this.fetchItems();
  },
  methods: {
    // 获取所有投票项及其票数
    async fetchItems() {
      try {
        const response = await axios.get('http://localhost:8080/api/voting-items-with-vote-count');
        this.items = response.data;
      } catch (error) {
        console.error('Error fetching items:', error);
      }
    },
    // 添加新投票项
    async addItem() {
      if (!this.newItemName.trim()) {
        alert('投票项名称不能为空');
        return;
      }
      
      try {
        await axios.post('http://localhost:8080/api', {
          name: this.newItemName
        });
        
        // 清空输入框并更新列表
        this.newItemName = '';
        this.fetchItems();
      } catch (error) {
        console.error('Error adding item:', error);
      }
    },
    // 提交投票
    async submitVotes() {
      if (!this.userName.trim()) {
        alert('請輸入您的名稱');
        return;
      }

      if (this.selectedItems.length === 0) {
        alert('请至少选择一个投票项');
        return;
      }

      try {
        await axios.post('http://localhost:8080/api/submit-votes', {
          itemIds: this.selectedItems,
          voter: this.userName
        });

        // 清空选择并更新列表
        this.selectedItems = [];
        this.userName = '';
        this.fetchItems();
        window.location.reload();
      } catch (error) {
        console.error('Error submitting votes:', error);
      }
    },
    // 删除投票项
    async deleteItem(id) {
      try {
        await axios.delete(`http://localhost:8080/api/delete-voting-items/${id}`);
        this.fetchItems();
      } catch (error) {
        console.error('Error deleting item:', error);
      }
    }
  },
};
</script>


<style scoped>
.container {
  width: 80%;
  margin: 0 auto;
  font-family: Arial, sans-serif;
}

h1 {
  text-align: center;
  margin-bottom: 30px;
  font-size: 2em;
  color: #333;
}

.vote-section {
  display: flex;
  align-items: center;
  margin: 20px auto;
  justify-content: center;
}

.user-name-section {
  margin-right: 20px; /* Add space between input and button */
}

.user-name-input {
  padding: 12px;
  border-radius: 5px;
  border: 1px solid #ddd;
  font-size: 16px;
  max-width: 200px; /* Adjust as needed */
}

.submit-votes-button {
  background-color: #2ecc71;
  color: #fff;
  border: none;
  padding: 12px 25px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

.submit-votes-button:hover {
  background-color: #27ae60;
}

.item-list {
  list-style-type: none;
  padding: 0;
  margin: 0 auto;
  max-width: 800px;
}

.item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f9f9f9;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.item-content {
  display: flex;
  align-items: center;
}

.item-name {
  font-size: 18px;
  margin-right: 20px;
  font-weight: bold;
}

.item-vote-count {
  font-size: 16px;
  color: #555;
}

.item-actions {
  display: flex;
  align-items: center;
}

.vote-label {
  display: flex;
  align-items: center;
  margin-right: 20px;
}

.vote-checkbox {
  margin-right: 10px;
  transform: scale(1.2);
}

.vote-description {
  font-size: 14px;
  color: #555;
}

.delete-button {
  background-color: #e74c3c;
  color: #fff;
  border: none;
  padding: 8px 12px;
  border-radius: 5px;
  cursor: pointer;
}

.delete-button:hover {
  background-color: #c0392b;
}

.add-item-section {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 30px;
}

.new-item-input {
  padding: 12px;
  border-radius: 5px;
  border: 1px solid #ddd;
  margin-right: 15px;
  flex: 1;
  max-width: 200px;
  box-sizing: border-box;
}

.add-item-button {
  background-color: #3498db;
  color: #fff;
  border: none;
  padding: 12px 25px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

.add-item-button:hover {
  background-color: #2980b9;
}
</style>
