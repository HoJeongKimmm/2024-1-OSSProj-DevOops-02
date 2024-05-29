import projectListIcon2Img from 'assets/images/main/project_list_icon2.png'
import { ProjectCard } from 'components/ProjectCard'
import projectListSampleJson from 'constants/json/project_list_sample.json'
import { FC, useState } from 'react'
import { ProjectListType } from 'types/project'
import { camelizeKey } from 'utils/camelizeKey'
import { Container, ProjectCardContainer, Root, TitleContainer, TitleLogoImg, LeftContainer, RightContainer, TitleTypo } from './styled'
import { stacks } from 'types/stacks'
import { Select } from 'antd'

type RecommendProjectListSectionProps = {
  className?: string
  recommendedProjectList: ProjectListType
}

export const RecommendProjectListSection: FC<RecommendProjectListSectionProps> = ({ className, recommendedProjectList }) => {
  const [selectedStack, setSelectedStack] = useState<string>()
  const projectList = recommendedProjectList
  const projectList_test = camelizeKey(projectListSampleJson.project_list) as ProjectListType

  return (
      <Root className={className}>
        <Container>
          <TitleContainer>
            <LeftContainer>
              <TitleLogoImg src={projectListIcon2Img} alt={'추천 프로젝트 로고 이미지'} />
              <TitleTypo>추천 프로젝트</TitleTypo>
            </LeftContainer>
            <RightContainer>
              <Select
                  onChange={(value) => setSelectedStack(value)}
                  value={selectedStack}
                  options={stacks}
                  size="large"
                  placeholder="추천 받을 프로젝트의 기술 스택"
                  style={{ width: 500 }}
              />
            </RightContainer>
          </TitleContainer>
          <ProjectCardContainer>
            {projectList
                .filter((projectItem) => !selectedStack || projectItem.requireMemberList.some((member) => member.developmentStack === selectedStack))
                .slice(0, 4)
                .map((projectItem) => (
                    <ProjectCard projectItem={projectItem} key={`project_card_${projectItem.key}`} />
                ))}
            {projectList_test
                .filter((projectItem) => !selectedStack || projectItem.requireMemberList.some((member) => member.developmentStack === selectedStack))
                .slice(0, 4)
                .map((projectItem) => (
                    <ProjectCard projectItem={projectItem} key={`project_card_test_${projectItem.key}`} />
                ))}
          </ProjectCardContainer>
        </Container>
      </Root>
  )
}
