package illyan.compose.scrollbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import illyan.compose.scrollbar.ui.theme.ComposeScrollbarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeScrollbarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScrollbarExampleScreen(
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScrollbarExampleScreen(
    modifier: Modifier = Modifier
) {
    val items = (0..50).toList()
    Column(
        modifier = modifier
    ) {
        val rowState = rememberLazyListState()
        LazyRow(
            modifier = Modifier
                .drawHorizontalScrollbar(rowState)
                .fillMaxSize()
                .weight(1f),
            state = rowState,
        ) {
            items(items) {
                Text(
                    modifier = Modifier
                        .vertical()
                        .rotate(90f)
                        .padding(16.dp),
                    text = "Item number $it"
                )
            }
        }
        val columnState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier
                .drawVerticalScrollbar(columnState)
                .fillMaxSize()
                .weight(1f),
            state = columnState
        ) {
            items(items) {
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "Item number $it",
                )
            }
        }
    }
}

fun Modifier.vertical() =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.height, placeable.width) {
            placeable.place(
                x = -(placeable.width / 2 - placeable.height / 2),
                y = -(placeable.height / 2 - placeable.width / 2)
            )
        }
    }