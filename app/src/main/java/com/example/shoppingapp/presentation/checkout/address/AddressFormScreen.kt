import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingapp.presentation.checkout.address.AddressUIEvent
import com.example.shoppingapp.presentation.product_info.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressFormScreen(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    viewModel: SharedViewModel = hiltViewModel()
) {

    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adding Shipping Address", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AddressTextField(value = state.fullName, onValueChange = { viewModel.onEvent(AddressUIEvent.NameChange(it))  }, label = "Full Name")
                AddressTextField(value = state.address, onValueChange = { viewModel.onEvent(AddressUIEvent.AddressChange(it)) }, label = "Address")
                AddressTextField(value = state.city, onValueChange = { viewModel.onEvent(AddressUIEvent.CityChange(it)) }, label = "City")
                AddressTextField(value = state.state, onValueChange = { viewModel.onEvent(AddressUIEvent.StateChange(it)) }, label = "State/Province/Region")
                AddressTextField(value = state.zipcode, onValueChange = { viewModel.onEvent(AddressUIEvent.ZipCodeChange(it)) }, label = "Zip Code (Postal Code)")

                Spacer(modifier = Modifier.height(16.dp))

                Button(

                    onClick = {
                        viewModel.onEvent(AddressUIEvent.SaveAddress)
                        onSaveClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C4DFE))
                ) {
                    Text("SAVE ADDRESS", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }
    )
}

@Composable
fun AddressTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),

    )
}

