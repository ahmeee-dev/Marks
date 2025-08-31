import { View, Text, StyleSheet  } from "react-native";
import { VELOCITY_EPS } from "react-native-reanimated/lib/typescript/animation/decay/utils";
import { SafeAreaView } from "react-native-safe-area-context";

const SupportButton = () => null;
const ProfileButton = () => null;
const RandomText = () => null;
const ArgumentPlaceHolder = () => null;
const QualityScroller = () => null;
const StartButton = () => null;

export default function Index() {
	return (
		<View>
			<View>
				<SupportButton/>
				<ProfileButton/>
			</View>
			<View>
				<RandomText/>
				<ArgumentPlaceHolder/>
			</View>
			<View>
				<QualityScroller/>
				<StartButton/>
			</View>
		</View>
		)
}