# LavaDeathLogger

**Paper 1.21.5 전용**  
InventoryRollbackPlus와 함께 사용하는 보조 플러그인으로, **플레이어가 용암에 의해 사망했을 때만 인벤토리 로그(YML)를 유지**하고, **다른 원인으로 사망한 경우 자동으로 해당 로그를 삭제**합니다.

## 기능 설명

- `InventoryRollbackPlus`는 기본적으로 모든 사망 로그를 저장합니다.
- 이 플러그인은 사망 원인이 `LAVA`가 아닌 경우:
  - `plugins/InventoryRollbackPlus/backups/deaths/<uuid>/` 폴더 내 `.yml` 파일들을 자동 삭제합니다.
- 서버의 불필요한 로그 누적 방지 및 특정 상황(용암 데스)만 로그를 남기도록 제한합니다.

## 설치 방법

1. `LavaDeathLogger.jar` 파일을 `plugins` 폴더에 넣으세요.
2. 서버를 재시작하거나 `/reload` 명령어를 실행하세요.
3. 플러그인이 제대로 동작하면 콘솔에 다음과 같은 메시지가 출력됩니다: [LavaDeathLogger] LavaDeathLogger 플러그인이 활성화되었습니다.


## 요구 사항

- **PaperMC 1.21.5 이상**
- **InventoryRollbackPlus 1.7.2**

## 빌드 방법

이 프로젝트는 **Maven**으로 구성되어 있습니다.

```bash
mvn clean package```

빌드 결과는 /target 폴더에 LavaDeathLogger.jar로 생성됩니다.

## 주의 사항
InventoryRollbackPlus의 로그 경로는 기본 설정을 따릅니다. 변경된 경우 해당 경로에 맞게 코드 수정이 필요할 수 있습니다.

이 플러그인은 서버 로그를 선택적으로 유지하려는 목적에 적합합니다. 모든 사망 로그를 보존하려는 환경에는 적합하지 않습니다
