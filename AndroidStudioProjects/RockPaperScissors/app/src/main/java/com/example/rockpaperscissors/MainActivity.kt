package com.example.rockpaperscissors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.rockpaperscissors.ui.theme.RockPaperScissorsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RockPaperScissorsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameStart(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GameStart(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            GameTopAppBar(modifier)
        },
        bottomBar = {
            GameBottomBar(modifier)
        },
        content = { innerPadding ->
            GameView(modifier = modifier.padding(innerPadding))
        },
        containerColor = Color(0xFFB0C4DE)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.game_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.rock_paper_scissors),
                    style = MaterialTheme.typography.displayMedium.copy(fontSize = 24.sp),
                    color = Color.White
                )
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF0B14CE)
        )
    )
}

@Composable
fun GameBottomBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF0B14CE))
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.created_by),
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun GameView(modifier: Modifier = Modifier) {
    var computerChoice by remember { mutableStateOf("") }
    var playerChoice by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val rock = painterResource(R.drawable.rock)
    val paper = painterResource(R.drawable.paper)
    val scissors = painterResource(R.drawable.scissors)

    val choices = listOf(
        stringResource(R.string.computer_chose_rock),
        stringResource(R.string.computer_chose_paper),
        stringResource(R.string.computer_chose_scissors)
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.game_logo),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        // Header text
        Text(
            text = stringResource(R.string.pick_your_choice),
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
        )

        // Row of buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    playerChoice = "Rock"
                    computerChoice = getComputerChoice(choices)
                    result = matchResult(playerChoice, computerChoice)
                },
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EE),
                    contentColor = Color.White
                )
            ) {
                Image(
                    painter = rock,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .zIndex(1f)
                )

            }

            Button(
                onClick = {
                    playerChoice = "Paper"
                    computerChoice = getComputerChoice(choices)
                    result = matchResult(playerChoice, computerChoice)
                },
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EE),
                    contentColor = Color.White
                )
            ) {
                Image(
                    painter = paper,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .zIndex(1f)
                )
            }

            Button(
                onClick = {
                    playerChoice = "Scissors"
                    computerChoice = getComputerChoice(choices)
                    result = matchResult(playerChoice, computerChoice)
                },
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EE),
                    contentColor = Color.White
                )
            ) {
                Image(
                    painter = scissors,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .zIndex(1f)
                )
            }
        }
        if (computerChoice.isNotEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(
                            Color(0xFF6200EE),
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.computer_chose),
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Spacer between texts
                    Text(
                        text = computerChoice,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                }

                // Result text
                Text(
                    text = result,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    color = if (result.contains(
                            "win",
                            true
                        )
                    ) Color.Green else Color.Red,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .background(
                            Color(0xFFF1F1F1),
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(16.dp)
                )
            }
        }
    }
}

fun getComputerChoice(choices: List<String>): String {
    return choices.random()
}

fun matchResult(
    playerChoice: String,
    computerChoice: String
): String {
    return when {
        playerChoice == computerChoice -> "It's a tie!"
        playerChoice == "Rock" && computerChoice == "Scissors" -> "You win!"
        playerChoice == "Paper" && computerChoice == "Rock" -> "You win!"
        playerChoice == "Scissors" && computerChoice == "Paper" -> "You win!"
        else -> "You lose!"
    }
}

@Preview(showBackground = true)
@Composable
fun GamePreview() {
    RockPaperScissorsTheme {
        GameStart(modifier = Modifier)
    }
}