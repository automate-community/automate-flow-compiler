package com.kweezy.autool.stmt;

import com.kweezy.autool.ObjectReader;
import com.kweezy.autool.ObjectWriter;
import com.kweezy.autool.stmt.blocks.*;
import com.kweezy.autool.stmt.functions.*;
import com.kweezy.autool.stmt.interfaces.BlockType;
import com.kweezy.autool.stmt.interfaces.readWriteData;
import com.kweezy.autool.stmt.types.*;
import com.kweezy.autool.stmt.types.constants.NullConstant;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BlocksInit {
    private final PreparedStatement<?>[] preparedblocks;
    private final Map<Class<?>, PreparedStatement<?>> idtoblock;


    public <T> PreparedStatement<T> c(final Class<T> clazz) {
        return (PreparedStatement<T>)this.idtoblock.get(clazz);
    }

    public BlocksInit(final PreparedStatement<?>... a) {
        this.preparedblocks = a;
        this.idtoblock = new HashMap<Class<?>, PreparedStatement<?>>(a.length);
        for (final PreparedStatement<?> d : a) {
            this.idtoblock.put(d.blockClass, d);
        }

        Arrays.sort(this.preparedblocks, PreparedStatement.comparator);
    }

    public static BlocksInit get() {
        return init.a;
    }

    public <T> PreparedStatement<T> getBlockById(final int n) {
        final PreparedStatement<?>[] PossibleListOfBlocks = this.preparedblocks;
        int n2 = PossibleListOfBlocks.length - 1;
        int i = 0;
        while (i <= n2) {
            final int n3 = i + n2 >>> 1;
            final int b = PossibleListOfBlocks[n3].blockId;
            if (b < n) {
                i = n3 + 1;
            }
            else {
                if (b <= n) {
                    return (PreparedStatement<T>)PossibleListOfBlocks[n3];
                }
                n2 = n3 - 1;
            }
        }
        return null;
    }

    private static class PrepareConstant<T extends readWriteData> extends PreparedStatement<T>
    {
        private final T d;

        public PrepareConstant(final int n, final T d) {
            super(n, (Class<T>) d.getClass());
            this.d = d;
        }

        @Override
        public T read(final ObjectReader a) {
            return a.addBlockToBlocksArray(this.d);
        }

        @Override
        public void write(final ObjectWriter b, final T t) {
        }
    }

    private static class PrepareStmt<T extends readWriteData> extends PreparedStatement<T> {

        public PrepareStmt(int id, final Class<T> cl) {
            super(id, cl);
        }

        @Override
        public T read(ObjectReader reader) {
            try {
                final readWriteData statement = (readWriteData) super.blockClass.getDeclaredConstructor().newInstance();
                reader.addBlockToBlocksArray(statement).readData(reader);

                return (T) statement;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException | IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void write(final ObjectWriter writer, final T p1) throws IOException {
            p1.writeData(writer);
        }
    }

    private static class init<T extends BlockType> {
        static final BlocksInit a;

        static {
            a = new BlocksInit(new PreparedStatement<?>[]{
                    //new PrepareStmt(100, com.llamalab.automate.expr.m.c.class), // BinaryNumber
                    new PrepareStmt(101, HexNumber.class),
                    new PrepareStmt(102, VariableName.class),
                    new PrepareConstant(103, NullConstant.x0),
                    new PrepareStmt(104, AutomateNumber.class),
                    new PrepareStmt(105, StringInterpolation.class),
                    new PrepareStmt(106, StringConstantValue.class),
                    new PrepareStmt(110, Addition.class),
                    new PrepareStmt(111, LogicalAnd.class),
                    //new PrepareStmt(112, com.llamalab.automate.expr.m.e.class),
                    //new PrepareStmt(113, f.class),
                    //new PrepareStmt(114, h.class),
                    //new PrepareStmt(115, i.class),
                    //new PrepareStmt(116, com.llamalab.automate.expr.m.j.class),
                    //new PrepareStmt(117, g.class),
                    //new PrepareStmt(118, k.class),
                    new PrepareStmt(119, Concatenation.class),
                    new PrepareStmt(120, TernaryOperator.class),
                    //new PrepareStmt(121, o.class),
                    new PrepareStmt(122, Equals.class),
                    //new PrepareStmt(123, q.class),
                    new PrepareStmt(124, GreaterThan.class),
                    //new PrepareStmt(125, s.class),
                    new PrepareStmt(126, GetLengthFunc.class),
                    //new PrepareStmt(127, x.class),
                    new PrepareStmt(128, LessThan.class),
                    //new PrepareStmt(129, z.class),
                    new PrepareStmt(130, Multiplication.class),
                    new PrepareStmt(131, Negate.class),
                    new PrepareStmt(132, AutomateArray.class),
                    new PrepareStmt(133, e0.class), // Dictionary??? (AutomateDictInExprA)
                    //new PrepareStmt(134, f0.class),
                    new PrepareStmt(135, NotEquals.class),
                    new PrepareStmt(136, LogicalOr.class),
                    new PrepareStmt(137, SubscriptOperator.class),
                    new PrepareStmt(138, Subtraction.class),
                    //new PrepareStmt(139, s0.class),
                    new PrepareStmt(140, ConvertToText.class),
                    //new PrepareStmt(141, v.class),
                    //new c(200, u.x0),
                    //new c(201, b0.x0),
                    //new c(202, h0.x0),
                    //new c(203, l0.x0),
                    //new PrepareStmt(300, Abs.class),
                    //new PrepareStmt(301, Acos.class),
                    //new PrepareStmt(302, Asin.class),
                    //new PrepareStmt(303, Atan.class),
                    //new PrepareStmt(304, Atan2.class),
                    //new PrepareStmt(305, Ceil.class),
                    new PrepareStmt(306, Contains.class),
                    //new PrepareStmt(307, CoordFormat.class),
                    //new PrepareStmt(308, Cos.class),
                    //new PrepareStmt(309, DateFormat.class),
                    //new PrepareStmt(310, DateParts.class),
                    //new PrepareStmt(311, Distance.class),
                    //new PrepareStmt(312, PhoneEqual.class),
                    //new PrepareStmt(313, Exp.class),
                    //new PrepareStmt(314, Floor.class),
                    new PrepareStmt(315, JsonDecode.class),
                    new PrepareStmt(316, JsonEncode.class),
                    //new PrepareStmt(317, Join.class),
                    //new PrepareStmt(318, LocalTime.class),
                    //new PrepareStmt(319, Log.class),
                    //new PrepareStmt(320, LowerCase.class),
                    //new PrepareStmt(321, Date.class),
                    //new PrepareStmt(322, Matches.class),
                    //new PrepareStmt(323, Max.class),
                    //new PrepareStmt(324, TimeMerge.class),
                    //new PrepareStmt(325, MimeType.class),
                    //new PrepareStmt(326, Min.class),
                    //new PrepareStmt(327, NumberFormat.class),
                    //new PrepareStmt(328, Pow.class),
                    //new PrepareStmt(329, ReplaceAll.class),
                    //new PrepareStmt(330, Round.class),
                    //new PrepareStmt(331, Sift.class),
                    //new PrepareStmt(332, Sin.class),
                    new PrepareStmt(333, Split.class),
                    //new PrepareStmt(334, Sqrt.class),
                    //new PrepareStmt(335, Tan.class),
                    new PrepareStmt(336, Trim.class),
                    //new PrepareStmt(337, Trunc.class),
                    //new PrepareStmt(338, UpperCase.class),
                    //new PrepareStmt(339, UrlEncode.class),
                    //new PrepareStmt(340, UtcTime.class),
                    //new PrepareStmt(341, CliEncode.class),
                    //new PrepareStmt(342, Glob.class),
                    //new PrepareStmt(343, Keys.class),
                    //new PrepareStmt(344, Shuffle.class),
                    //new PrepareStmt(345, DurationFormat.class),
                    //new PrepareStmt(346, Substr.class),
                    new PrepareStmt(347, Storage.class),
                    //new PrepareStmt(348, Copy.class),
                    new PrepareStmt(349, Random.class),
                    //new PrepareStmt(350, HexEncode.class),
                    //new PrepareStmt(351, Char.class),
                    //new PrepareStmt(352, UrlDecode.class),
                    //new PrepareStmt(353, Bearing.class),
                    //new PrepareStmt(354, Celsius.class),
                    //new PrepareStmt(355, Fahrenheit.class),
                    //new PrepareStmt(356, Type.class),
                    //new PrepareStmt(357, XmlDecode.class),
                    //new PrepareStmt(358, XmlEncode.class),
                    //new PrepareStmt(359, Base64Decode.class),
                    //new PrepareStmt(360, Base64Encode.class),
                    //new PrepareStmt(361, HexDecode.class),
                    //new PrepareStmt(362, Sum.class),
                    //new PrepareStmt(363, Ctz.class),
                    //new PrepareStmt(364, Values.class),
                    //new PrepareStmt(365, Declination.class),
                    //new PrepareStmt(366, DateParse.class),
                    //new PrepareStmt(367, Filter.class),
                    //new PrepareStmt(368, Concat.class),
                    //new PrepareStmt(369, Extend.class),
                    //new PrepareStmt(370, Intersect.class),
                    //new PrepareStmt(371, Sort.class),
                    //new PrepareStmt(372, Time.class),
                    //new PrepareStmt(373, Disjoint.class),
                    //new PrepareStmt(374, PhoneFormat.class),
                    //new PrepareStmt(375, Reverse.class),
                    //new PrepareStmt(376, Distinct.class),
                    //new PrepareStmt(377, Levenshtein.class),
                    //new PrepareStmt(378, Crc32.class),
                    //new PrepareStmt(379, Md5.class),
                    new PrepareStmt(380, Sha1.class),
                    //new PrepareStmt(381, SqlEncode.class),
                    //new PrepareStmt(382, IndexOf.class),
                    //new PrepareStmt(383, Slice.class),
                    new PrepareStmt(384, FindAll.class),
                    //new PrepareStmt(385, VersionCompare.class),
                    //new PrepareStmt(386, ColorRecode.class),
                    //new PrepareStmt(387, TimePart.class),
                    //new PrepareStmt(388, ConvType.class),
                    //new PrepareStmt(389, Clock.class),
                    //new PrepareStmt(390, Except.class),
                    //new PrepareStmt(391, Union.class),
                    //new PrepareStmt(392, FileUri.class),
                    //new PrepareStmt(393, Sha256.class),
                    //new PrepareStmt(394, XPathEncode.class),
                    //new PrepareStmt(395, SizeFormat.class),
                    new PrepareStmt(1000, AccountPick.class),
                    //new PrepareStmt(1001, ActivityStart.class),
                    new PrepareStmt(1002, ActivityStartResult.class),
                    //new PrepareStmt(1003, AirplaneModeEnabled.class),
                    //new PrepareStmt(1004, AmbientLight.class),
                    //new PrepareStmt(1005, AmbientTemperature.class),
                    //new PrepareStmt(1006, AppForeground.class),
                    //new PrepareStmt(1007, AppInstalled.class),
                    //new PrepareStmt(1008, AppKillBackground.class),
                    new PrepareStmt(1009, ArrayAdd.class),
                    //new PrepareStmt(1010, ArrayRemove.class),
                    new PrepareStmt(1011, ArraySet.class),
                    new PrepareStmt(1012, VariableAssign.class),
                    //new PrepareStmt(1013, AssistRequest.class),
                    //new PrepareStmt(1014, AtmosphericPressure.class),
                    //new PrepareStmt(1015, AudioRecordStart.class),
                    //new PrepareStmt(1016, AudioRecordStop.class),
                    //new PrepareStmt(1017, AudioVolume.class),
                    //new PrepareStmt(1018, AudioVolumeSet.class),
                    //new PrepareStmt(1019, AccountSyncEnabled.class),
                    //new PrepareStmt(1020, AccountSyncSetState.class),
                    //new PrepareStmt(1021, BatteryLevel.class),
                    new PrepareStmt(1022, BroadcastReceive.class),
                    //new PrepareStmt(1023, BroadcastSend.class),
                    //new PrepareStmt(1024, CallAnswer.class),
                    //new PrepareStmt(1025, CallEnd.class),
                    //new PrepareStmt(1026, CallIncoming.class),
                    //new PrepareStmt(1027, CallNumber.class),
                    //new PrepareStmt(1028, CallOutgoing.class),
                    //new PrepareStmt(1029, CallState.class),
                    //new PrepareStmt(1030, CellSignalLevel.class),
                    new PrepareStmt(1031, CaptureImage.class),
                    //new PrepareStmt(1032, ClipboardGet.class),
                    //new PrepareStmt(1033, ClipboardSet.class),
                    //new PrepareStmt(1034, ComposeEmail.class),
                    //new PrepareStmt(1035, ComposeMms.class),
                    //new PrepareStmt(1036, ComposeSms.class),
                    //new PrepareStmt(1037, ContactQuery.class),
                    //new PrepareStmt(1038, ContactPick.class),
                    new PrepareStmt(1039, ContentCopy.class),
                    //new PrepareStmt(1040, ContentPick.class),
                    //new PrepareStmt(1041, ContentShared.class),
                    new PrepareStmt(1042, ContentView.class),
                    //new PrepareStmt(1043, DatePick.class),
                    //new PrepareStmt(1044, RingtoneGet.class),
                    //new PrepareStmt(1045, RingtoneSet.class),
                    //new PrepareStmt(1046, Delay.class),
                    //new PrepareStmt(1047, DeviceDocked.class),
                    //new PrepareStmt(1048, DeviceLock.class),
                    //new PrepareStmt(1049, DeviceOrientation.class),
                    //new PrepareStmt(1050, DeviceUnlocked.class),
                    //new PrepareStmt(1051, DialNumber.class),
                    new PrepareStmt(1052, DialogChoice.class),
                    new PrepareStmt(1053, DialogConfirm.class),
                    new PrepareStmt(1054, DialogInput.class),
                    new PrepareStmt(1055, DictionaryPut.class),
                    new PrepareStmt(1056, DictionaryRemove.class),
                    //new PrepareStmt(1057, DurationPick.class),
                    new PrepareStmt(1058, ExpressionDecision.class),
                    //new PrepareStmt(1059, EmailSend.class),
                    new PrepareStmt(1060, Fork.class),
                    //new PrepareStmt(1061, FiberStop.class),
                    new PrepareStmt(1062, FileDelete.class),
                    //new PrepareStmt(1063, FileCopy.class),
                    //new PrepareStmt(1064, FileList.class),
                    //new PrepareStmt(1065, FileMonitor.class),
                    new PrepareStmt(1066, FileMakeDirectory.class),
                    //new PrepareStmt(1067, FileMove.class),
                    new PrepareStmt(1068, FilePick.class),
                    new PrepareStmt(1069, FileRead.class),
                    new PrepareStmt(1070, FileWrite.class),
                    //new PrepareStmt(1071, Flashlight.class),
                    new PrepareStmt(1072, FlowBeginning.class),
                    new PrepareStmt(1073, ForEach.class),
                    //new PrepareStmt(1074, FtpDelete.class),
                    new PrepareStmt(1075, FtpDownload.class),
                    //new PrepareStmt(1076, FtpList.class),
                    //new PrepareStmt(1077, FtpUpload.class),
                    new PrepareStmt(1078, MediaStoreAdd.class),
                    //new PrepareStmt(1079, GDriveDelete.class),
                    //new PrepareStmt(1080, GDriveDownload.class),
                    //new PrepareStmt(1081, GDriveList.class),
                    new PrepareStmt(1082, GDriveUpload.class),
                    new PrepareStmt(1083, GmailSend.class),
                    //new PrepareStmt(1084, Geocoding.class),
                    //new PrepareStmt(1085, GeocodingReverse.class),
                    new PrepareStmt(1087, HttpRequest.class),
                    //new PrepareStmt(1088, LocationAt.class),
                    new PrepareStmt(1089, LocationGet.class),
                    //new PrepareStmt(1090, LocationPick.class),
                    //new PrepareStmt(1091, LocationShow.class),
                    new PrepareStmt(1092, LocationProviderEnabled.class),
                    new PrepareStmt(1093, LogAppend.class),
                    //new PrepareStmt(1094, MediaButton.class),
                    //new PrepareStmt(1095, MicrophoneMuted.class),
                    //new PrepareStmt(1096, MicrophoneSetMute.class),
                    //new PrepareStmt(1097, WifiNetworkPick.class),
                    //new PrepareStmt(1098, NetworkType.class),
                    //new PrepareStmt(1099, NfcEnabled.class),
                    //new PrepareStmt(1100, NfcTagScanned.class),
                    //new PrepareStmt(1101, NfcTagWrite.class),
                    //new PrepareStmt(1102, NotificationCancel.class),
                    new PrepareStmt(1103, NotificationShow.class),
                    //new PrepareStmt(1104, PlugInCondition.class),
                    //new PrepareStmt(1105, PlugInEvent.class),
                    //new PrepareStmt(1106, PlugInSetting.class),
                    //new PrepareStmt(1107, PasswordFailed.class),
                    //new PrepareStmt(1108, PowerSourcePlugged.class),
                    //new PrepareStmt(1109, Proximity.class),
                    //new PrepareStmt(1110, Roaming.class),
                    //new PrepareStmt(1111, RingerMode.class),
                    //new PrepareStmt(1112, RingerModeSet.class),
                    //new PrepareStmt(1113, ScreenBrightness.class),
                    //new PrepareStmt(1114, ScreenBrightnessSet.class),
                    //new PrepareStmt(1116, ScreenOffTimeout.class),
                    //new PrepareStmt(1117, ScreenOffTimeoutSet.class),
                    //new PrepareStmt(1118, DeviceInteractive.class),
                    //new PrepareStmt(1119, ScreenOrientation.class),
                    new PrepareStmt(1120, ToastShow.class),
                    //new PrepareStmt(1121, SmsReceived.class),
                    //new PrepareStmt(1122, SmsSend.class),
                    //new PrepareStmt(1123, SmsSent.class),
                    //new PrepareStmt(1124, SoundPlay.class),
                    //new PrepareStmt(1125, SoundStop.class),
                    //new PrepareStmt(1126, SpeechRecognition.class),
                    //new PrepareStmt(1127, SpeakPlay.class),
                    //new PrepareStmt(1128, SpeakStop.class),
                    //new PrepareStmt(1129, SpeakToFile.class),
                    //new PrepareStmt(1130, SpeakerphoneOn.class),
                    //new PrepareStmt(1131, SpeakerphoneSetState.class),
                    //new PrepareStmt(1132, StorageMediaMounted.class),
                    //new PrepareStmt(1133, StorageSpace.class),
                    //new PrepareStmt(1134, SystemSettingGet.class),
                    //new PrepareStmt(1135, SystemSettingSet.class),
                    //new PrepareStmt(1136, TakePicture.class),
                    //new PrepareStmt(1137, TimeWindow.class),
                    new PrepareStmt(1138, TimePick.class),
                    //new PrepareStmt(1139, VibrateStart.class),
                    //new PrepareStmt(1140, VibrateStop.class),
                    //new PrepareStmt(1141, WallpaperImageSet.class),
                    new PrepareStmt(1142, Weather.class),
                    //new PrepareStmt(1143, WifiApEnabled.class),
                    //new PrepareStmt(1144, WifiApSetState.class),
                    //new PrepareStmt(1145, WifiNetworkConnect.class),
                    //new PrepareStmt(1146, WifiNetworkConnected.class),
                    //new PrepareStmt(1147, WifiEnabled.class),
                    //new PrepareStmt(1148, WifiNetworkScan.class),
                    //new PrepareStmt(1149, WifiSetState.class),
                    new PrepareStmt(1150, ZipExtract.class),
                    new PrepareStmt(1151, ZipCompress.class),
                    //new PrepareStmt(1152, AudioPlayerControl.class),
                    //new PrepareStmt(1153, BluetoothDeviceConnected.class),
                    //new PrepareStmt(1154, BluetoothDevicePick.class),
                    //new PrepareStmt(1155, BluetoothEnabled.class),
                    //new PrepareStmt(1156, BluetoothSetState.class),
                    //new PrepareStmt(1157, GmailUnreadCount.class),
                    //new PrepareStmt(1158, ServiceStart.class),
                    //new PrepareStmt(1159, ShellCommand.class),
                    //new PrepareStmt(1160, WiredHeadset.class),
                    new PrepareStmt(1161, FlowStop.class),
                    //new PrepareStmt(1162, MobileDataEnabled.class),
                    //new PrepareStmt(1163, MobileDataSetState.class),
                    //new PrepareStmt(1164, ShortcutStart.class),
                    //new PrepareStmt(1165, AirplaneModeSetState.class),
                    //new PrepareStmt(1166, LocationProviderSetState.class),
                    //new PrepareStmt(1167, NfcSetState.class),
                    //new PrepareStmt(1168, ScreenOrientationSet.class),
                    new PrepareStmt(1169, TimeAwait.class),
                    //new PrepareStmt(1170, CalendarEventAdd.class),
                    //new PrepareStmt(1171, CalendarPick.class),
                    //new PrepareStmt(1172, FlowBeginningPick.class),
                    new PrepareStmt(1173, FlowStart.class),
                    new PrepareStmt(1174, FileExists.class),
                    //new PrepareStmt(1175, RingtonePick.class),
                    //new PrepareStmt(1176, RingerSilence.class),
                    //new PrepareStmt(1177, InputMethodPick.class),
                    //new PrepareStmt(1178, InputMethodSet.class),
                    //new PrepareStmt(1179, PhysicalActivity.class),
                    //new PrepareStmt(1180, ShellCommandSuperuser.class),
                    //new PrepareStmt(1181, GoogleAssistantAction.class),
                    //new PrepareStmt(1182, AlarmAdd.class),
                    //new PrepareStmt(1183, ScreenLockSetState.class),
                    //new PrepareStmt(1184, CellSiteNear.class),
                    //new PrepareStmt(1185, CellSitePick.class),
                    //new PrepareStmt(1186, CalendarEventGet.class),
                    //new PrepareStmt(1187, CalendarEventQuery.class),
                    //new PrepareStmt(1188, SystemLanguageGet.class),
                    //new PrepareStmt(1189, TimeZoneGet.class),
                    //new PrepareStmt(1190, NetworkConnected.class),
                    //new PrepareStmt(1191, NotificationInteract.class),
                    //new PrepareStmt(1192, NotificationPosted.class),
                    //new PrepareStmt(1193, BluetoothTetherEnabled.class),
                    //new PrepareStmt(1194, BluetoothTetherSetState.class),
                    //new PrepareStmt(1195, FiberStopped.class),
                    //new PrepareStmt(1196, ResolveActivity.class),
                    //new PrepareStmt(1197, ResolveReceiver.class),
                    //new PrepareStmt(1198, ResolveService.class),
                    //new PrepareStmt(1200, ScreenLockSetState.a.class),
                    //new PrepareStmt(1201, CarModeEnabled.class),
                    //new PrepareStmt(1202, CarModeSetState.class),
                    //new PrepareStmt(1203, NightModeEnabled.class),
                    //new PrepareStmt(1204, NightModeSetState.class),
                    //new PrepareStmt(1205, AttentionLight.class),
                    //new PrepareStmt(1206, ColorPick.class),
                    //new PrepareStmt(1207, DataUsage.class),
                    //new PrepareStmt(1208, NetworkThroughput.class),
                    //new PrepareStmt(1209, MobileOperator.class),
                    //new PrepareStmt(1210, Alarm.class),
                    //new PrepareStmt(1211, BluetoothDeviceConnect.class),
                    //new PrepareStmt(1212, CpuSpeedGet.class),
                    //new PrepareStmt(1213, CpuSpeedSet.class),
                    //new PrepareStmt(1214, VariablesGive.class),
                    //new PrepareStmt(1215, VariablesTake.class),
                    //new PrepareStmt(1216, VariablesTake.a.class),
                    //new PrepareStmt(1115, DeviceKeepAwake.class),
                    //new PrepareStmt(1217, DeviceKeepAwake.a.class),
                    //new PrepareStmt(1218, DeviceReboot.class),
                    //new PrepareStmt(1219, DeviceShutdown.class),
                    //new PrepareStmt(1220, MotionGesture.class),
                    //new PrepareStmt(1221, AppKill.class),
                    //new PrepareStmt(1222, BluetoothScoSetState.class),
                    //new PrepareStmt(1223, BluetoothScoSetState.a.class),
                    //new PrepareStmt(1224, TonePlay.class),
                    //new PrepareStmt(1225, UsbTetherEnabled.class),
                    //new PrepareStmt(1226, UsbTetherSetState.class),
                    //new PrepareStmt(1227, Screenshot.class),
                    new PrepareStmt(1228, Ping.class),
                    //new PrepareStmt(1229, Interact.class),
                    //new PrepareStmt(1230, AccountSyncRequest.class),
                    //new PrepareStmt(1231, WakeOnLanSend.class),
                    //new PrepareStmt(1232, PowerSaveModeEnabled.class),
                    //new PrepareStmt(1233, PowerSaveModeSetState.class),
                    //new PrepareStmt(1234, MobileNetworkPreferredSet.class),
                    //new PrepareStmt(1235, AppClearCache.class),
                    new PrepareStmt(1236, AccountGenericAdd.class),
                    //new PrepareStmt(1237, AppPick.class),
                    //new PrepareStmt(1238, MediaPlaying.class),
                    //new PrepareStmt(1239, MediaTagsRead.class),
                    //new PrepareStmt(1240, TimerAdd.class),
                    //new PrepareStmt(1241, WifiSignalLevel.class),
                    //new PrepareStmt(1242, AppNotificationsEnabled.class),
                    //new PrepareStmt(1243, AppNotificationsSetState.class),
                    //new PrepareStmt(1244, AndroidVersion.class),
                    //new PrepareStmt(1245, InterruptionFilter.class),
                    //new PrepareStmt(1246, InterruptionFilterSet.class),
                    //new PrepareStmt(1247, FtpMakeDirectory.class),
                    //new PrepareStmt(1248, GDriveMakeDirectory.class),
                    //new PrepareStmt(1249, MmsSend.class),
                    //new PrepareStmt(1250, AppOpMode.class),
                    //new PrepareStmt(1251, AppOpModeSet.class),
                    //new PrepareStmt(1252, MobileNetworkPreferred.class),
                    new PrepareStmt(1253, AtomicAdd.class),
                    new PrepareStmt(1254, AtomicClearAll.class),
                    //new PrepareStmt(1255, AtomicCompareAndStore.class),
                    new PrepareStmt(1256, AtomicLoad.class),
                    new PrepareStmt(1257, AtomicStore.class),
                    //new PrepareStmt(1258, DeviceAcceleration.class),
                    //new PrepareStmt(1259, MagneticFieldStrength.class),
                    //new PrepareStmt(1260, WallpaperLiveSet.class),
                    //new PrepareStmt(1261, FlowPick.class),
                    //new PrepareStmt(1262, MediaStoreRemove.class),
                    new PrepareStmt(1263, FailureCatch.class),
                    //new PrepareStmt(1264, x0.class),
                    //new PrepareStmt(1265, CloudMessageReceive.class),
                    //new PrepareStmt(1266, CloudMessageSend.class),
                    //new PrepareStmt(1267, SubscriptionPick.class),
                    //new PrepareStmt(1268, SystemPropertyGet.class),
                    //new PrepareStmt(1269, ToastPosted.class),
                    //new PrepareStmt(1270, BluetoothDeviceDisconnect.class),
                    //new PrepareStmt(1271, TimeZoneSet.class),
                    //new PrepareStmt(1272, VideoRecordStart.class),
                    //new PrepareStmt(1273, VideoRecordStop.class),
                    //new PrepareStmt(1274, ScreenLockSetState.c.class),
                    //new PrepareStmt(1275, com.llamalab.automate.stmt.d0.class),
                    //new PrepareStmt(1276, NotificationAction.class),
                    //new PrepareStmt(1277, BluetoothDeviceScan.class),
                    //new PrepareStmt(1278, Subroutine.class),
                    //new PrepareStmt(1279, GDriveShare.class),
                    new PrepareStmt(1280, DialogWeb.class),
                    //new PrepareStmt(1281, DeviceRestart.class),
                    //new PrepareStmt(1282, InfraredTransmit.class),
                    //new PrepareStmt(1283, SignificantDeviceMotion.class),
                    //new PrepareStmt(1284, SoundLevel.class),
                    //new PrepareStmt(1285, CyanogenModProfile.class),
                    //new PrepareStmt(1286, CyanogenModProfileSet.class),
                    new PrepareStmt(1287, Goto.class),
                    new PrepareStmt(1288, Label.class),
                    //new PrepareStmt(1289, KeyboardVisible.class),
                    //new PrepareStmt(1290, MobileDataNetworkType.class),
                    //new PrepareStmt(1291, DeviceIdleModeActive.class),
                    //new PrepareStmt(1292, RestrictBackgroundDataEnabled.class),
                    //new PrepareStmt(1293, RestrictBackgroundDataSetState.class),
                    //new PrepareStmt(1294, ContentChanged.class),
                    //new PrepareStmt(1295, ContentDelete.class),
                    //new PrepareStmt(1296, ContentInsert.class),
                    new PrepareStmt(1297, ContentQuery.class),
                    //new PrepareStmt(1298, ContentUpdate.class),
                    //new PrepareStmt(1299, DeviceIdleModeSetState.class),
                    //new PrepareStmt(1300, Pedometer.class),
                    //new PrepareStmt(1301, SystemLanguageSet.class),
                    new PrepareStmt(1302, DialogNumber.class),
                    //new PrepareStmt(1303, QuickSettingsTileShow.class),
                    //new PrepareStmt(1304, com.llamalab.automate.stmt.n0.class),
                    //new PrepareStmt(1305, AppList.class),
                    //new PrepareStmt(1306, AppNotificationsPriorityGet.class),
                    //new PrepareStmt(1307, AppNotificationsPrioritySet.class),
                    //new PrepareStmt(1308, AppNotificationsVisibilityGet.class),
                    //new PrepareStmt(1309, AppNotificationsVisibilitySet.class),
                    //new PrepareStmt(1310, AppUsage.class),
                    //new PrepareStmt(1311, MobileServiceState.class),
                    //new PrepareStmt(1312, NotificationPolicyGet.class),
                    //new PrepareStmt(1313, NotificationPolicySet.class),
                    //new PrepareStmt(1314, ProcessText.class),
                    //new PrepareStmt(1315, ProcessTextResult.class),
                    //new PrepareStmt(1316, LocationMock.class),
                    //new PrepareStmt(1317, AudioStreamMuted.class),
                    //new PrepareStmt(1318, AudioStreamSetMute.class),
                    //new PrepareStmt(1319, DatabaseModify.class),
                    //new PrepareStmt(1320, DatabaseQuery.class),
                    //new PrepareStmt(1321, NotificationSnooze.class),
                    //new PrepareStmt(1322, FingerprintGesture.class),
                    //new PrepareStmt(1323, UssdRequest.class),
                    //new PrepareStmt(1324, KeyPressed.class),
                    //new PrepareStmt(1325, InteractTouch.class),
                    //new PrepareStmt(1326, BroadcastSendOrdered.class),
                    //new PrepareStmt(1327, Fullscreen.class),
                    //new PrepareStmt(1328, PreferredActivity.class),
                    //new PrepareStmt(1329, AudioDeviceConnected.class),
                    //new PrepareStmt(1330, DisplayOn.class),
                    //new PrepareStmt(1331, ToneStop.class),
                    //new PrepareStmt(1332, NotificationChannelPick.class),
                    //new PrepareStmt(1333, HotwordDetected.class),
                    //new PrepareStmt(1334, AccessibilityButton.class),
                    new PrepareStmt(1335, DialogMessage.class),
                    //new PrepareStmt(1336, AlternativeLaunch.class),
                    //new PrepareStmt(1337, LogAwait.class),
                    //new PrepareStmt(1338, KeySend.class),
                    //new PrepareStmt(1339, HeartRate.class),
                    //new PrepareStmt(1340, HingeAngle.class),
                    //new PrepareStmt(1341, RelativeHumidity.class),
                    //new PrepareStmt(1342, AdbShellCommand.class),
                    //new PrepareStmt(1343, KeyChainAliasPick.class),
                    //new PrepareStmt(1344, NsdDiscover.class),
                    //new PrepareStmt(1345, CaptureVideo.class),
                    //new PrepareStmt(1346, ActivityStartVoice.class),
                    //new PrepareStmt(1347, ContentOffer.class),
                    //new PrepareStmt(1348, ContentOfferResult.class),
                    //new PrepareStmt(1349, AudioDeviceRecording.class),
                    //new PrepareStmt(1350, ShortcutPin.class),
                    //new PrepareStmt(1351, IconPick.class),
                    //new PrepareStmt(1352, UserAsleep.class),
                    //new PrepareStmt(1353, DisplayQuery.class),
                    //new PrepareStmt(1354, InspectLayout.class),
                    //new PrepareStmt(1355, CallScreening.class),
                    //new PrepareStmt(1356, CallScreeningResponse.class),
                    //new PrepareStmt(1357, SplitScreenModeEnabled.class),
                    //new PrepareStmt(1358, ZipList.class),
                    //new PrepareStmt(1359, SubscriptionSetState.class),
                    //new PrepareStmt(1360, com.llamalab.automate.stmt.s.class), // ?
                    //new PrepareStmt(1361, ImageLoad.class),
                    //new PrepareStmt(1362, ImageUnload.class),
                    //new PrepareStmt(1363, ImageWrite.class),
                    new PrepareStmt(1364, ImageCrop.class),
                    new PrepareStmt(1365, ImageFlip.class),
                    new PrepareStmt(1366, ImageRescale.class),
                    new PrepareStmt(1367, ImageRotate.class),
                    new PrepareStmt(1368, ImageSampleColor.class),
                    new PrepareStmt(1369, BatteryCharging.class),
                    new PrepareStmt(1370, BatteryProperties.class),
                    new PreparedStatement<Integer>(16, (Class)Integer.class) {
                        public Integer read(final ObjectReader a) throws IOException {
                            return a.addBlockToBlocksArray(a.transform_35());
                        }

                        public void write(final ObjectWriter b, final Integer n) throws IOException {
                            b.transform_35(n);
                        }
                    }
            });

        }
    }

}

