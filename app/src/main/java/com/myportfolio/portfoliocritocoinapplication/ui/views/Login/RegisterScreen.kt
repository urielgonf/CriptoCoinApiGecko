package com.myportfolio.portfoliocritocoinapplication.ui.views.Login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myportfolio.portfoliocritocoinapplication.R
import com.myportfolio.portfoliocritocoinapplication.ui.navigation.Screens
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.SingUpViewModel

@Composable
fun RegisterView(
    navController: NavController,
    singUpViewModel: SingUpViewModel
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rpassword by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .imePadding()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.gecko),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(200.dp) // Set fixed height for the image
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = userName,
            onValueChange ={userName = it},
            label = { Text(text = "User Name")},
            keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .padding(start = 30.dp, end = 30.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange ={email = it},
            label = { Text(text = "Email")},
            keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .padding(start = 30.dp, end = 30.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange ={password = it},
            label = { Text(text = "Password")},
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }

                IconButton(onClick = {
                    passwordVisible = !passwordVisible
                }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .padding(start = 30.dp, end = 30.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = rpassword,
            onValueChange ={rpassword = it},
            label = { Text(text = "Confirm password")},
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .padding(start = 30.dp, end = 30.dp),


            )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {

                          singUpViewModel.createUser(
                              email,
                              password,
                              rpassword,
                              userName,
                              {
                                  navController.navigate(Screens.SingUp.route)
                                  Toast.makeText(context, "Welcome ${userName} you are a new user Now ", Toast.LENGTH_SHORT).show()
                              },
                              {
                                  Toast.makeText(context, " Passwords are no equal ", Toast.LENGTH_SHORT).show()

                              },
                              {
                                  Toast.makeText(context, " The email is already used", Toast.LENGTH_SHORT).show()
                              },
                              {
                                  Toast.makeText(context, " The User is alredy used ", Toast.LENGTH_SHORT).show()
                              }
                          )

                      },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        ) {
            Text(text = "Sign In")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(Screens.HomeScreen.route) }) {
            Text(text = "Skip Register")
        }
        

    }
    


}