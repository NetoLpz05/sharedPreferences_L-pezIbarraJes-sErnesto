package lopez.ernesto.login

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import lopez.ernesto.login.ui.theme.LoginTheme
import androidx.compose.ui.text.input.PasswordVisualTransformation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = PreferenceManager(this)

        enableEdgeToEdge()
        setContent {
            var screenState by remember { mutableStateOf(if (prefs.isLoggedIn()) "HOME" else "LOGIN")}

            if (screenState == "LOGIN"){
                LoginScreen(onLoginClick= {
                    screenState = "HOME"
                    prefs.saveLoginStatus(true)

                })
            } else {
                HomeScreen(onLogoutClick = {

                    screenState = "HOME"
                    prefs.logout()
                })
            }
        }
    }
}

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pantalla de Login", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            {email = it; errorMessage =""},
            label = {Text("Correo electrónico")},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            {password = it; errorMessage =""},
            label = {Text("Contraseña")},
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        if(errorMessage.isNotEmpty()){
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            if (email == "itskirito05@gmail.com" && password == "luffyyonko"){
                onLoginClick()
            } else {
                errorMessage = "Credenciales incorrectas. Intenta de nuevo..."
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Entrar")
        }
    }
}

@Composable
fun HomeScreen(onLogoutClick: () -> Unit) {
    Column(
        Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bienvenido al Home!", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {onLogoutClick()}) {
            Text("Cerrar Sesión")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {

}