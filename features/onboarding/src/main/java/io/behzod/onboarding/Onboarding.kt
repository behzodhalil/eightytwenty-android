package io.behzod.onboarding

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import io.behzod.features.R
import io.behzod.features.Roboto
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Onboarding() {
    val context = LocalContext.current

    val items = OnboardingItem.items()
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    var isLastPage by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopContent {
            if (pagerState.currentPage + 1 < items.size) {
                scope.launch {
                    pagerState.animateScrollToPage(items.size - 1)
                    isLastPage = true
                }
            }
        }
        HorizontalPager(
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth(),
            count = 4,
            state = pagerState,
            userScrollEnabled = false
        ) { page ->
            OnboardingContent(
                item = items[page],
                size = items.size,
                index = pagerState.currentPage
            )
        }
        BottomContent(
            isLastPage = isLastPage,
            onNextClick = {
                if (pagerState.currentPage + 1 < items.size) {
                    scope.launch {
                        pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        isLastPage = pagerState.currentPage == items.size - 1
                    }
                }
            },
            onBeProductivityClick = {
                Toast.makeText(context, "Hey", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Composable
fun OnboardingContent(
    modifier: Modifier = Modifier,
    item: OnboardingItem,
    size: Int,
    index: Int,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = item.image),
            contentDescription = "",
            modifier = modifier
                .padding(start = 25.dp, end = 25.dp)
        )

        Spacer(modifier = modifier.height(23.dp))

        Text(
            text = stringResource(id = item.title),
            fontSize = 24.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.kashmir_blue),
            textAlign = TextAlign.Left,
            modifier = modifier.padding(start = 25.dp),
            letterSpacing = 1.sp
        )

        Spacer(modifier = modifier.height(14.dp))

        Text(
            text = stringResource(id = item.description),
            fontSize = 14.sp,
            fontFamily = Roboto,
            style = MaterialTheme.typography.subtitle2,
            fontWeight = FontWeight.Normal,
            color = colorResource(id = R.color.bel_air_blue),
            textAlign = TextAlign.Left,
            modifier = modifier.padding(start = 25.dp, end = 25.dp),
            letterSpacing = 1.sp
        )
        Spacer(modifier = modifier.height(20.dp))

        Indicators(modifier = modifier.padding(start = 25.dp), size, index)

    }
}

@Composable
fun TopContent(
    modifier: Modifier = Modifier,
    onSkipClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onSkipClick()
            },
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.skip),
            fontSize = 18.sp,
            modifier = modifier.padding(4.dp),
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = modifier.width(2.dp))

        Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")
    }
}

@Composable
fun BottomContent(
    modifier: Modifier = Modifier,
    isLastPage: Boolean,
    onNextClick: () -> Unit,
    onBeProductivityClick: () -> Unit,
) {
    TextButton(
        onClick = { if (isLastPage) onBeProductivityClick() else onNextClick() }, colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.sun_glow),
            contentColor = colorResource(id = R.color.white)
        ),
        modifier = modifier.padding(start = 25.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        if (isLastPage) {
            Text(
                text = stringResource(id = R.string.become_productivity),
                fontSize = 18.sp,
                modifier = modifier.padding(4.dp),
                fontWeight = FontWeight.Medium
            )
        } else {
            Text(
                text = stringResource(id = R.string.continues),
                fontSize = 18.sp,
                modifier = modifier.padding(4.dp),
                fontWeight = FontWeight.Medium
            )
        }

    }
}

@Composable
fun ColumnScope.Indicators(modifier: Modifier = Modifier, size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Preview
@Composable
fun OnboardingPreview() {
    Surface {
        Onboarding()
    }

}

