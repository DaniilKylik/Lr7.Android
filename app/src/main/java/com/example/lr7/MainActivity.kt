package com.example.lr7

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lr7.data.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.lr7.data.model.User
import com.example.lr7.data.model.Order

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserOrderAdapter
    private lateinit var buttonLoadData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserOrderAdapter(listOf())
        recyclerView.adapter = adapter

        buttonLoadData = findViewById(R.id.button_load_data)

        val db = AppDatabase.getDatabase(this)

        // Додаємо дані в базу при запуску програми
//        lifecycleScope.launch(Dispatchers.IO) {
//            // Створюємо об'єкти User
//            val user1 = User(name = "John Doe", email = "john.doe@example.com")
//            val user2 = User(name = "Jane Smith", email = "jane.smith@example.com")
//
//            val userId1 = db.userDao().insertUser(user1)
//            val userId2 = db.userDao().insertUser(user2)
//
//            val order1 = Order(orderDetails = "Order 1 details", userId = userId1)
//            val order2 = Order(orderDetails = "Order 2 details", userId = userId2)
//
//            // Вставляємо замовлення в базу даних
//            db.orderDao().insertOrder(order1)
//            db.orderDao().insertOrder(order2)
//        }
        // 7. Отримання даних з таблиць User та Order
        lifecycleScope.launch(Dispatchers.IO) {
            val userDao = db.userDao()
            val orderDao = db.orderDao()

            val users = userDao.getAllUsers()
            val orders = orderDao.getAllOrders()

            // Переключаємося на головний потік для оновлення UI
            withContext(Dispatchers.Main) {
                users.observe(this@MainActivity, Observer { userList ->
                    // Оновлюємо адаптер новими даними користувачів
                    adapter.updateData(userList.map { it.name })
                })
                orders.observe(this@MainActivity, Observer { orderList ->
                })
            }
        }

        // 8. Отримання об'єднаних даних через JOIN
        lifecycleScope.launch(Dispatchers.Main) {
            db.userOrderDao().getUserOrders().observe(this@MainActivity, Observer { userOrders ->
                // Оновлюємо адаптер з об'єднаними даними (ім'я користувача + деталі замовлення)
                userOrders?.let {
                    adapter.updateData(it.map { "${it.name}: ${it.order_details}" })
                }
            })
        }
    }
}