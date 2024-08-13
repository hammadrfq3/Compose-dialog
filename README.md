
# Customizable Compose Dialogs

An Android library that creates different dialogs with various customization options. Quick, efficient and easy to implement.


## Showcase

<table style="border: none;">
<tr>
<td width="25%"> 
 <img src="https://github.com/user-attachments/assets/aeeb63d8-3c79-4a7f-8ebf-a2bb0f5af17a" /></td>
<td width="25%"> 
<img src="https://github.com/user-attachments/assets/5c71ad88-2b21-4283-84ce-22636a7d99cc" /></td>
</tr>
<tr>
<td width="25%"> 
 <img src="https://github.com/user-attachments/assets/8111068b-0d2c-4ee2-bdb6-89a3819671c7" /></td>
<td width="25%"> 
<img src="https://github.com/user-attachments/assets/d35ea58e-3939-4a6b-a38b-51e113308ea9" /></td>
</tr>
<tr>
<td width="25%"> 
 <img src="https://github.com/user-attachments/assets/fe967adc-a838-471a-9fdd-062f3d54a0d9" /></td>
<td width="25%"> 
<img src="https://github.com/user-attachments/assets/0ca9c6e6-3484-4f0c-b7fa-04beddb0a415" /></td>
</tr>
<tr>
<td width="25%"> 
 <img src="https://github.com/user-attachments/assets/817ef007-5d8e-4faa-953e-162397884645" /></td>
<td width="25%"> 
<img src="https://github.com/user-attachments/assets/bef8abda-8418-472a-b353-bf0f7c07ec52" /></td>
</tr>
</table>

# Get started

In your top-level `build.gradle` file:

```bash
  repositories {
     ...
     mavenCentral()
     maven { url 'https://jitpack.io' }
  }
```

In your app-level `build.gradle` file:

```bash
  dependencies {
     ...
     implementation 'com.github.hammadrfq3:Compose-dialog:1.0'
  }
```

### Usage

```bash
@Composable
fun showSimpleDialog() {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog)
        SimpleDialog(
            title = "Simple Dialog",
            buttonShape = CircleShape,
            description = "A dialog is a type of modal window that appears in front of app content to provide critical information, or prompt for a decision to be made.",
            textAlign = TextAlign.Start,
            onDismiss = {
                showDialog = false
            }) {
              showDialog = false
     }
 }
```

Checkout the implmentation of other types of dialogs on <a href="https://github.com/hammadrfq3/Compose-dialog/blob/master/app/src/main/java/com/dialog/composedialog/MainActivity.kt" title="Code">MainActivity</a>

### Dialog parameters

- `title`: String (Set title of dialog)
- `description`: String (Set description of dialog)
- `positiveButtonText`: String (default: "Ok")
- `negativeButtonText`: String (default: "Cancel")
- `buttonShape`: Shape (default: RoundedCornerShape(15.dp))
- `dialogBackgroundColor`: Color (default: colorResource(id = R.color.white))
- `negativeButtonBackgroundColor`: Color (default: colorResource(id = R.color.white))
- `positiveButtonBackgroundColor`: Color (default: colorResource(id = R.color.dialog_black_btn))
- `positiveButtonTextColor`: Color (default: colorResource(id = R.color.white))
- `negativeButtonTextColor`: Color (default: colorResource(id = R.color.text_dark))
- `titleTextColor`: Color (default: colorResource(id = R.color.black))
- `descriptionTextColor`: Color (default: colorResource(id = R.color.light_grey))
- `isCancelable`: Boolean (default: true)
- `isEditableDialog`: Boolean (default: false)               // To show editable dialog
- `isAnimatedDialog`: Boolean (default: false)               // To show animated dialog
- `isPickerDialog`: Boolean (default: false)               // To show Time picker dialog
- `textAlign`: TextAlign (default: TextAlign.Center)
- `@RawRes lottieAnimatorRes`: Int? (default: null)
- `@DrawableRes image`: Int? (default: null)             
- `onDismiss`: () -> Unit
- `onDoneClick`: (Any) -> Unit


## Find this library useful ? ❤️

- Support it by clicking the ⭐️ button on the upper right of this page and share with others. ✌️


