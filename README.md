# crm-lib

The shared library for the CRM microservice fleet. One jar (`com.crm:crm-lib`) holding the
cross-cutting infrastructure that every CRM service needs, so that adding service number *n* means
adding a dependency instead of copying a package.


## Consumers

| Service            |
| ------------------ |
| `crm-workflow`     |
| `crm-account`      |
| `crm-master-setup` |

Every new service is expected to depend on this.

## Using it

```xml
<properties>
    <crm-lib.version>1.0.0</crm-lib.version>
</properties>

<dependency>
    <groupId>com.crm</groupId>
    <artifactId>crm-lib</artifactId>
    <version>${crm-lib.version}</version>
</dependency>
```

## Building

```bash
mvn install       # installs 1.0.0 into the local ~/.m2 so the services resolve it
```