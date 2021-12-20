# Disaster Recovery Overview

Liferay DXP Cloud offers two strategies for disaster recovery: Automatic and Cross-Region.

## Automatic Disaster Recovery Strategy

DXP Cloud performs automatic disaster recovery protocols by distributing service instances between three zones in different geographic locations within the same region, and restarting services in a different zone if one zone goes down. In case any of the zones become unavailable, the Load Balancer will automatically route to the remaining zones without requiring DNS changes on the customer side. In this situation, *no action is required from the customer during an incident*.

## Cross-Region Disaster Recovery Strategies

There are multiple Cross-Region DR strategies in the IT industry which have been classified as "cold", "warm", or "hot". These strategies indicate how prepared the system is to recover when something goes wrong. One analogy is the response if you were driving and had a flat tire. How you deal with this situation depends on how equipped you are:

| Type | Solution |
| --- | --- |
| Cold | You have no spare tire so you must call someone to come to you with a new tire. Your trip stops until someone comes and replaces the tire. |
| Warm | You have a spare tire and a replacement kit so you can get back on the road using what you have in your car. However, you must stop to repair the problem. |
| Hot | You have special run-flat tires. You might need to slow down a little, but there is no immediate impact on your journey. Your tires run well enough that you can continue (although you must eventually address the issue). |

The DR strategy in DXP Cloud is _Warm_. DXP Cloud users do not need to contact Support in order to respond to a disaster scenario, but are required to act in the case of a cross-region disaster to use the provided tools.

## Disaster Recovery Example Scenario

Here is an example of how the DXP Cloud Disaster Recovery tools and approaches would work in the case of a fictional disaster scenario.

### During an Incident

| Time | Event |
| --- | --- |
| 12:30 pm | Everything is normal in the Production environment (located in _europe-west2_ region). Users are able to access the system with no problems. |
| 1:00 pm | The hourly automatic backup is triggered. This generates a copy of the Database and Document Library. |
| 2:00 pm | The automatic backup is triggered again. |
| 2:34 pm | You are notified that the entire _europe-west2_ region has been compromised. You must move to enable the DR environment (located in the _us-west1_ region) in order to minimize downtime. From 2:00 pm until 2:34 pm no backups generated. |
| 2:39 pm | You restore the 2:00 pm backup of the Production database and Document Library to the DR environment. |
| 2:41 pm | The latest Build number is retrieved from Production and deployed to the DR environment. |
| 2:45 pm | Your team validates that the VPN has been configured properly in the DR environment. |
| 2:50 pm | A Custom Domain is configured to route user traffic to the DR environment. |
| 3:00 pm | Everything is back to normal and now traffic is routed through the DR environment. |

The amount of time the above steps take depends on the size of your application and how fast your team will be able to respond.

### Post-Incident Recovery

| Time | Event |
| --- | --- |
| 4:00 pm | Your team is notified that the _europe-west2_ region has recovered and the incident has concluded. The team prepares to migrate back to the original Production environment. |
| 4:16 pm | A manual backup of the DR environment is performed, since there has been new user data generated during the disaster incident. |
| 4:21 pm | The manual backup is restored the Production environment and the Custom Domain configurations returned to their original state. |
| 4:25 pm | Traffic is re-routed back to the original Production environment. |

## Additional Information

* [Configuring Cross-Region Disaster Recovery](./configuring-cross-region-disaster-recovery.md)
