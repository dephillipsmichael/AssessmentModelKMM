package org.sagebionetworks.assessmentmodel.presentation.compose

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.sagebionetworks.assessmentmodel.presentation.AssessmentViewModel
import org.sagebionetworks.assessmentmodel.presentation.ui.theme.BackgroundGray
import org.sagebionetworks.assessmentmodel.presentation.ui.theme.sageH1
import org.sagebionetworks.assessmentmodel.presentation.ui.theme.sageP2
import org.sagebionetworks.assessmentmodel.survey.ChoiceQuestion
import org.sagebionetworks.assessmentmodel.survey.QuestionState

@Composable
internal fun QuestionHeader(
    subtitle: String?,
    title: String?,
    detail: String?,
    assessmentViewModel: AssessmentViewModel,
    scrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    val elevationValue = if (scrollState.value > 5) { 5.dp} else {0.dp}
    val headerElevation = animateDpAsState(targetValue = elevationValue)
    Card(
        elevation = headerElevation.value,
        modifier = Modifier.padding(bottom = 4.dp)
    ) {

        Column(
            modifier = modifier
                .background(BackgroundGray),
        ) {
            ProgressBar(progress = assessmentViewModel.assessmentNodeState.progress())
            val openDialog = remember { mutableStateOf(false) }
            PauseScreenDialog(
                showDialog = openDialog.value,
                assessmentViewModel = assessmentViewModel
            ) {
                openDialog.value = false
            }
            PauseTopBar(
                onPauseClicked = { openDialog.value = true },
                onSkipClicked = { assessmentViewModel.skip() })
            Column(
                modifier = modifier
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                subtitle?.let { subtitle ->
                    Text(
                        text = subtitle,
                        style = sageP2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 0.dp, bottom = 0.dp, start = 24.dp, end = 8.dp)
                    )
                }
                title?.let { title ->
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = title,
                        style = sageH1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 0.dp, bottom = 0.dp, start = 24.dp, end = 8.dp)
                    )
                }
                detail?.let { detail ->
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = detail,
                        style = sageP2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 0.dp, bottom = 0.dp, start = 24.dp, end = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}