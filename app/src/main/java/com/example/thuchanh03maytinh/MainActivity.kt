package com.example.thuchanh03maytinh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MayTinh()
        }
    }
}
@Composable
fun MayTinh () {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var selectedOp by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(200.dp))
        // 1. Text tiêu đề "Thực hành 03"
        Text(
            text = "Thực hành 03",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(30.dp))
        // 2. OutlinedTextField cho số thứ nhất (value = num1, onValueChange = { num1 = it })
        OutlinedTextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Nhập số") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // 3. Row chứa 4 nút phép toán
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { selectedOp = "+" },
                modifier = Modifier.size(52.dp),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("+", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { selectedOp = "-" },
                modifier = Modifier.size(52.dp),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE5A93C))
            ) {
                Text("-", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { selectedOp = "*" },
                modifier = Modifier.size(52.dp),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5B50EC))
            ) {
                Text("*", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { selectedOp = "/" },
                modifier = Modifier.size(52.dp),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("/", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 4. OutlinedTextField cho số thứ hai (value = num2, onValueChange = { num2 = it })
        OutlinedTextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Nhập số") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 5. Tính toán và hiển thị kết quả
        val n1 = num1.toDoubleOrNull()
        val n2 = num2.toDoubleOrNull()

        val result = when {
            n1 == null || n2 == null || selectedOp.isEmpty() -> ""
            selectedOp == "+" -> (n1 + n2).let { if (it % 1 == 0.0) it.toLong().toString() else it.toString() }
            selectedOp == "-" -> (n1 - n2).let { if (it % 1 == 0.0) it.toLong().toString() else it.toString() }
            selectedOp == "*" -> (n1 * n2).let { if (it % 1 == 0.0) it.toLong().toString() else it.toString() }
            selectedOp == "/" -> if (n2 == 0.0) "Không thể chia cho 0" else (n1 / n2).let { if (it % 1 == 0.0) it.toLong().toString() else it.toString() }
            else -> ""
        }

        Text(
            text = "Kết quả: $result",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
