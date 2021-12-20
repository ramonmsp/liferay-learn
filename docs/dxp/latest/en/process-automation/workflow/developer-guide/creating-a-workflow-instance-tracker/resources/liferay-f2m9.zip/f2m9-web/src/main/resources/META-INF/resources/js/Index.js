import ClayLink from '@clayui/link';
import ClayModal, {useModal} from '@clayui/modal';
import WorkflowInstanceTracker from '@liferay/portal-workflow-instance-tracker-web/js/components/WorkflowInstanceTracker';
import React, {useState} from 'react';

import '../css/main.scss'

export default function Index({workflowInstanceId}) {
	const [showInstanceTrackerModal, setShowInstanceTrackerModal] = useState(
		false
	);

	const {observer} = useModal({
		onClose: () => {
			setShowInstanceTrackerModal(false);
		},
	});

	return (
		<>
			<ClayLink onClick={() => setShowInstanceTrackerModal(true)}>
				{Liferay.Language.get('track-workflow')}
			</ClayLink>

			{showInstanceTrackerModal && (
				<ClayModal observer={observer} size="full-screen">
					<ClayModal.Header>
						{Liferay.Language.get('track-workflow')}
					</ClayModal.Header>

					<ClayModal.Body>
						<WorkflowInstanceTracker
							workflowInstanceId={workflowInstanceId}
						/>
					</ClayModal.Body>
				</ClayModal>
			)}
		</>
	);
}