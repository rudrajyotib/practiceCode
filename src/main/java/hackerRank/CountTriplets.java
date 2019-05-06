package hackerRank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class CountTriplets {
    private final List<Long> array;
    private final long commonRatio;
    private final Map<Long, ExistenceTrail> trailMap;
    private long tripletCount = 0;

    public CountTriplets(List<Long> array, long commonRatio) {
        this.array = array;
        this.commonRatio = commonRatio;
        this.trailMap = new HashMap<>(array.size());
    }

    public long countTriplets() {
        array.forEach(inputMember -> {
            ExistenceTrail existenceTrailOfPotentialStart = null;
            if ((inputMember % commonRatio) == 0) {
                existenceTrailOfPotentialStart = trailMap.get(inputMember / commonRatio);
                if (existenceTrailOfPotentialStart != null) {
                    tripletCount += existenceTrailOfPotentialStart.getPotentialMid();
                }
            }
            if (!trailMap.containsKey(inputMember)) {
                ExistenceTrail selfExistenceTrail = new ExistenceTrail();
                selfExistenceTrail.updatePotentialMid(existenceTrailOfPotentialStart);
                trailMap.put(inputMember, selfExistenceTrail);
            } else {
                ExistenceTrail currentExistenceTrail = trailMap.get(inputMember);
                currentExistenceTrail.updatePotentialMid(existenceTrailOfPotentialStart);
                currentExistenceTrail.incrementExistence();
            }

        });
        return tripletCount;
    }

}

@SuppressWarnings("WeakerAccess")
class ExistenceTrail {
    private long existence = 1;
    private long potentialMid = 0;

    public long getExistence() {
        return existence;
    }

    public long getPotentialMid() {
        return potentialMid;
    }

    public void incrementExistence() {
        ++existence;
    }

    public void updatePotentialMid(ExistenceTrail existenceTrail) {
        if (existenceTrail == null) {
            return;
        }
        potentialMid += existenceTrail.getExistence();
    }
}
